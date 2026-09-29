package com.portalattendance

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class PortalAttendanceApplication

fun main(args: Array<String>) {
	runApplication<PortalAttendanceApplication>(*args)
}
