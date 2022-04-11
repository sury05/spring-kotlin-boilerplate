package com.starter.kopring.exceptions

import com.starter.kopring.enums.LogLevel
import org.springframework.http.HttpStatus

class BadRequestException(
    message: String,
    override val logLevel: LogLevel = LogLevel.WARN
) : BaseException(message) {
    override fun getHttpStatus(): HttpStatus {
        return HttpStatus.BAD_REQUEST
    }
}
