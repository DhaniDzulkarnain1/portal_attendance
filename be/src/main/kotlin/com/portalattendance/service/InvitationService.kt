package com.portalattendance.service

import com.portalattendance.config.InvitationProperties
import com.portalattendance.dto.request.ConfirmAttendanceRequest
import com.portalattendance.dto.request.CreateInvitationRequest
import com.portalattendance.dto.response.InvitationResponse
import com.portalattendance.dto.response.QuotaResponse
import com.portalattendance.entity.ConfirmationStatus
import com.portalattendance.entity.Invitation
import com.portalattendance.exception.ConflictException
import com.portalattendance.exception.InvitationAccessDeniedException
import com.portalattendance.exception.ResourceNotFoundException
import com.portalattendance.repository.InvitationRepository
import com.portalattendance.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.util.UriComponentsBuilder
import java.time.OffsetDateTime

@Service
class InvitationService(
	private val invitationRepository: InvitationRepository,
	private val userRepository: UserRepository,
	private val qrCodeService: QrCodeService,
	private val invitationProperties: InvitationProperties,
) {

	@Transactional
	fun create(request: CreateInvitationRequest): InvitationResponse {
		val badgeId = request.badgeId.trim()
		val user = userRepository.findByBadgeId(badgeId)
			?: throw ResourceNotFoundException("Peserta dengan BADGE $badgeId tidak ditemukan")
		if (invitationRepository.existsByUserId(requireNotNull(user.id))) {
			throw ConflictException("Undangan untuk BADGE $badgeId sudah dibuat")
		}
		val invitation = invitationRepository.save(Invitation(user = user))
		return toResponse(invitation)
	}

	@Transactional(readOnly = true)
	fun findAll(): List<InvitationResponse> =
		invitationRepository.findAllByOrderByCreatedAtDesc().map(::toResponse)

	@Transactional(readOnly = true)
	fun findByBadgeId(badgeId: String): InvitationResponse =
		toResponse(getInvitation(badgeId))

	@Transactional
	fun confirm(badgeId: String, request: ConfirmAttendanceRequest): InvitationResponse {
		val invitation = getInvitation(badgeId)
		if (invitation.confirmationStatus != ConfirmationStatus.PENDING) {
			throw ConflictException("Undangan sudah dikonfirmasi dengan status ${invitation.confirmationStatus}")
		}
		val attending = request.attending == true
		if (attending && getQuota().remaining <= 0) {
			throw ConflictException("Kuota penuh, konfirmasi kehadiran tidak dapat diproses")
		}
		invitation.confirmationStatus = if (attending) ConfirmationStatus.HADIR else ConfirmationStatus.TIDAK_HADIR
		invitation.confirmedAt = OffsetDateTime.now()
		return toResponse(invitation)
	}

	@Transactional(readOnly = true)
	fun getQuota(): QuotaResponse {
		val capacity = invitationProperties.capacity
		val confirmed = invitationRepository.countByConfirmationStatus(ConfirmationStatus.HADIR)
		return QuotaResponse(
			capacity = capacity,
			confirmed = confirmed,
			remaining = (capacity - confirmed).coerceAtLeast(0),
		)
	}

	@Transactional(readOnly = true)
	fun generateQrCode(badgeId: String): ByteArray {
		val invitation = getInvitation(badgeId)
		return qrCodeService.generatePng(invitationUrl(invitation.user.badgeId))
	}

	private fun getInvitation(badgeId: String): Invitation =
		invitationRepository.findByUserBadgeId(badgeId.trim())
			?: throw InvitationAccessDeniedException("Akses ditolak: BADGE $badgeId tidak terdaftar dalam daftar undangan")

	private fun invitationUrl(badgeId: String): String =
		UriComponentsBuilder.fromUriString(invitationProperties.baseUrl)
			.pathSegment(badgeId)
			.encode()
			.toUriString()

	private fun toResponse(invitation: Invitation): InvitationResponse =
		InvitationResponse.from(invitation, invitationUrl(invitation.user.badgeId))
}
