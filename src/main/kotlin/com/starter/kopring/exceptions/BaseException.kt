package com.starter.kopring.exceptions

import com.starter.kopring.enums.LogLevel
import org.springframework.http.HttpStatus

abstract class BaseException(message: String) : RuntimeException(message) {
    abstract val logLevel: LogLevel

    abstract fun getHttpStatus(): HttpStatus
}
