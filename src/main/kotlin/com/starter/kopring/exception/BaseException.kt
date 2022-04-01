package com.starter.kopring.exception

import org.springframework.http.HttpStatus

abstract class BaseException(message: String) : RuntimeException(message) {
    abstract val logLevel: LogLevel

    abstract fun getHttpStatus(): HttpStatus
}
