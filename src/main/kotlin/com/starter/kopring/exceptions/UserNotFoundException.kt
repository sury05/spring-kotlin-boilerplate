package com.starter.kopring.exceptions

import com.starter.kopring.enums.LogLevel
import org.springframework.http.HttpStatus

class UserNotFoundException(
    message: String,
    override val logLevel: LogLevel = LogLevel.WARN
) : BaseException(message) {
    override fun getHttpStatus(): HttpStatus {
        return HttpStatus.NOT_FOUND
    }
}
