package com.portalattendance.dto.response

data class ApiResponse<T>(
	val success: Boolean,
	val message: String,
	val data: T? = null,
) {
	companion object {
		fun <T> success(message: String, data: T? = null) = ApiResponse(true, message, data)

		fun <T> error(message: String, data: T? = null) = ApiResponse(false, message, data)
	}
}
