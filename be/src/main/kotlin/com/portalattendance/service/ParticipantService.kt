package com.portalattendance.service

import com.portalattendance.dto.request.CreateParticipantRequest
import com.portalattendance.dto.response.ParticipantResponse
import com.portalattendance.entity.User
import com.portalattendance.exception.ConflictException
import com.portalattendance.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ParticipantService(
	private val userRepository: UserRepository,
) {

	@Transactional
	fun create(request: CreateParticipantRequest): ParticipantResponse {
		val badgeId = request.badgeId.trim()
		if (userRepository.existsByBadgeId(badgeId)) {
			throw ConflictException("BADGE $badgeId sudah terdaftar")
		}
		val user = userRepository.save(
			User(
				badgeId = badgeId,
				name = request.name.trim(),
				department = request.department?.trim(),
				position = request.position?.trim(),
			),
		)
		return ParticipantResponse.from(user)
	}

	@Transactional(readOnly = true)
	fun findAll(): List<ParticipantResponse> =
		userRepository.findAllByOrderByCreatedAtDesc().map(ParticipantResponse::from)
}
