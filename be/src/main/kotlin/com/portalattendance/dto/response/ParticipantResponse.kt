package com.portalattendance.dto.response

import com.portalattendance.entity.User
import java.time.OffsetDateTime

data class ParticipantResponse(
	val id: Long,
	val badgeId: String,
	val name: String,
	val department: String?,
	val position: String?,
	val createdAt: OffsetDateTime,
) {
	companion object {
		fun from(user: User) = ParticipantResponse(
			id = requireNotNull(user.id),
			badgeId = user.badgeId,
			name = user.name,
			department = user.department,
			position = user.position,
			createdAt = user.createdAt,
		)
	}
}
