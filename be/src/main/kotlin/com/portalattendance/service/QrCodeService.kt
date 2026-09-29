package com.portalattendance.service

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream

@Service
class QrCodeService {

	fun generatePng(content: String, size: Int = 300): ByteArray {
		val matrix = QRCodeWriter().encode(
			content,
			BarcodeFormat.QR_CODE,
			size,
			size,
			mapOf(EncodeHintType.MARGIN to 1),
		)
		return ByteArrayOutputStream().use { output ->
			MatrixToImageWriter.writeToStream(matrix, "PNG", output)
			output.toByteArray()
		}
	}
}
