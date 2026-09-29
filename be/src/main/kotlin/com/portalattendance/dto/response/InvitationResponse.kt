package com.portalattendance.dto.response

import com.portalattendance.entity.ConfirmationStatus
import com.portalattendance.entity.Invitation
import java.time.OffsetDateTime

data class InvitationResponse(
	val id: Long,
	val badgeId: String,
	val name: String,
	val department: String?,
	val position: String?,
	val seatId: String?,
	val confirmationStatus: ConfirmationStatus,
	val confirmedAt: OffsetDateTime?,
	val createdAt: OffsetDateTime,
	val invitationUrl: String,
) {
	companion object {
		fun from(invitation: Invitation, invitationUrl: String) = InvitationResponse(
			id = requireNotNull(invitation.id),
			badgeId = invitation.user.badgeId,
			name = invitation.user.name,
			department = invitation.user.department,
			position = invitation.user.position,
			seatId = invitation.seatId,
			confirmationStatus = invitation.confirmationStatus,
			confirmedAt = invitation.confirmedAt,
			createdAt = invitation.createdAt,
			invitationUrl = invitationUrl,
		)
	}
}
