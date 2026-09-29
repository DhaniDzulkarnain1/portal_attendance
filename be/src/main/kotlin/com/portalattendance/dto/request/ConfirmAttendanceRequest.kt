package com.portalattendance.dto.request

import jakarta.validation.constraints.NotNull

data class ConfirmAttendanceRequest(
	@field:NotNull
	val attending: Boolean?,
)
