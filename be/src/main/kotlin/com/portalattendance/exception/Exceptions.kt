package com.portalattendance.exception

class ResourceNotFoundException(message: String) : RuntimeException(message)

class ConflictException(message: String) : RuntimeException(message)

class InvitationAccessDeniedException(message: String) : RuntimeException(message)
