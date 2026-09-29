package com.portalattendance.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.OffsetDateTime

@Entity
@Table(name = "users")
class User(
	@Column(name = "badge_id", nullable = false, unique = true, length = 50)
	var badgeId: String,

	@Column(name = "name", nullable = false, length = 150)
	var name: String,

	@Column(name = "department", length = 100)
	var department: String? = null,

	@Column(name = "position", length = 150)
	var position: String? = null,

	@Column(name = "created_at")
	var createdAt: OffsetDateTime = OffsetDateTime.now(),

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,
)
