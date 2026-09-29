package com.portalattendance.repository

import com.portalattendance.entity.ConfirmationStatus
import com.portalattendance.entity.Invitation
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface InvitationRepository : JpaRepository<Invitation, Long> {

	@EntityGraph(attributePaths = ["user"])
	fun findByUserBadgeId(badgeId: String): Invitation?

	@EntityGraph(attributePaths = ["user"])
	fun findAllByOrderByCreatedAtDesc(): List<Invitation>

	fun existsByUserId(userId: Long): Boolean

	fun countByConfirmationStatus(confirmationStatus: ConfirmationStatus): Long
}
