package com.portalattendance.exception

import com.portalattendance.dto.response.ApiResponse
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException::class)
	fun handleNotFound(exception: ResourceNotFoundException) =
		errorResponse(HttpStatus.NOT_FOUND, exception.message)

	@ExceptionHandler(InvitationAccessDeniedException::class)
	fun handleAccessDenied(exception: InvitationAccessDeniedException) =
		errorResponse(HttpStatus.FORBIDDEN, exception.message)

	@ExceptionHandler(ConflictException::class)
	fun handleConflict(exception: ConflictException) =
		errorResponse(HttpStatus.CONFLICT, exception.message)

	@ExceptionHandler(DataIntegrityViolationException::class)
	fun handleDataIntegrity(exception: DataIntegrityViolationException) =
		errorResponse(HttpStatus.CONFLICT, "Data bertentangan dengan data yang sudah ada")

	@ExceptionHandler(HttpMessageNotReadableException::class)
	fun handleUnreadable(exception: HttpMessageNotReadableException) =
		errorResponse(HttpStatus.BAD_REQUEST, "Format request tidak valid")

	@ExceptionHandler(MethodArgumentNotValidException::class)
	fun handleValidation(exception: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Map<String, String>>> {
		val errors = exception.bindingResult.fieldErrors.associate { it.field to (it.defaultMessage ?: "tidak valid") }
		return ResponseEntity.badRequest().body(ApiResponse.error("Validasi gagal", errors))
	}

	private fun errorResponse(status: HttpStatus, message: String?): ResponseEntity<ApiResponse<Nothing>> =
		ResponseEntity.status(status).body(ApiResponse.error(message ?: status.reasonPhrase))
}
