package com.portalattendance.dto.response

data class QuotaResponse(
	val capacity: Long,
	val confirmed: Long,
	val remaining: Long,
)
