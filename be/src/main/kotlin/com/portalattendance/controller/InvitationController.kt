package com.portalattendance.controller

import com.portalattendance.dto.request.ConfirmAttendanceRequest
import com.portalattendance.dto.request.CreateInvitationRequest
import com.portalattendance.dto.response.ApiResponse
import com.portalattendance.dto.response.InvitationResponse
import com.portalattendance.service.InvitationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/invitations")
@Tag(name = "Undangan")
class InvitationController(
	private val invitationService: InvitationService,
) {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Buat undangan untuk peserta")
	fun create(@Valid @RequestBody request: CreateInvitationRequest): ApiResponse<InvitationResponse> =
		ApiResponse.success("Undangan berhasil dibuat", invitationService.create(request))

	@GetMapping
	@Operation(summary = "Daftar undangan")
	fun findAll(): ApiResponse<List<InvitationResponse>> =
		ApiResponse.success("Daftar undangan", invitationService.findAll())

	@GetMapping("/{badgeId}")
	@Operation(summary = "Buka undangan berdasarkan BADGE")
	fun findByBadgeId(@PathVariable badgeId: String): ApiResponse<InvitationResponse> =
		ApiResponse.success("Undangan ditemukan", invitationService.findByBadgeId(badgeId))

	@PostMapping("/{badgeId}/confirm")
	@Operation(summary = "Konfirmasi kehadiran peserta")
	fun confirm(
		@PathVariable badgeId: String,
		@Valid @RequestBody request: ConfirmAttendanceRequest,
	): ApiResponse<InvitationResponse> =
		ApiResponse.success("Konfirmasi kehadiran berhasil disimpan", invitationService.confirm(badgeId, request))

	@GetMapping("/{badgeId}/qr", produces = [MediaType.IMAGE_PNG_VALUE])
	@Operation(summary = "QR code undangan")
	fun qrCode(@PathVariable badgeId: String): ByteArray =
		invitationService.generateQrCode(badgeId)
}
