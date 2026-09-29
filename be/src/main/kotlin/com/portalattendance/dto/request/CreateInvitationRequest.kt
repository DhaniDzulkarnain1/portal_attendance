package com.portalattendance.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateInvitationRequest(
	@field:NotBlank
	@field:Size(max = 50)
	val badgeId: String,

	@field:Size(max = 50)
	val seatId: String? = null,
)
