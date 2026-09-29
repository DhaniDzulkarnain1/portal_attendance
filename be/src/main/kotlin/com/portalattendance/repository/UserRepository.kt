package com.portalattendance.repository

import com.portalattendance.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

	fun findByBadgeId(badgeId: String): User?

	fun existsByBadgeId(badgeId: String): Boolean

	fun findAllByOrderByCreatedAtDesc(): List<User>
}
