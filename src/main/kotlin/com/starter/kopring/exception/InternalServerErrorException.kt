package com.starter.kopring.exception

import org.springframework.http.HttpStatus

class InternalServerErrorException(
    message: String = "internal server error",
    override val logLevel: LogLevel = LogLevel.ERROR
) : BaseException(message) {
    override fun getHttpStatus(): HttpStatus {
        return HttpStatus.INTERNAL_SERVER_ERROR
    }
}
