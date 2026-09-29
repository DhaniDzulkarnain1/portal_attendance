package com.portalattendance.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.OffsetDateTime

@Entity
@Table(name = "invitations")
class Invitation(
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	var user: User,

	@Enumerated(EnumType.STRING)
	@JdbcTypeCode(SqlTypes.NAMED_ENUM)
	@Column(name = "confirmation_status", nullable = false, columnDefinition = "confirmation_status_enum")
	var confirmationStatus: ConfirmationStatus = ConfirmationStatus.PENDING,

	@Column(name = "seat_id", length = 50)
	var seatId: String? = null,

	@Column(name = "confirmed_at")
	var confirmedAt: OffsetDateTime? = null,

	@Column(name = "created_at")
	var createdAt: OffsetDateTime = OffsetDateTime.now(),

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,
)
