package com.portalattendance.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app.invitation")
data class InvitationProperties(
	val baseUrl: String,
)
