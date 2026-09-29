package com.portalattendance.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateParticipantRequest(
	@field:NotBlank
	@field:Size(max = 50)
	val badgeId: String,

	@field:NotBlank
	@field:Size(max = 150)
	val name: String,

	@field:Size(max = 100)
	val department: String? = null,

	@field:Size(max = 150)
	val position: String? = null,
)
