package com.starter.kopring.exception.handler

import com.starter.kopring.dto.ErrorResponse
import com.starter.kopring.exception.WebClientAuthException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.reactive.function.client.WebClientException

@ControllerAdvice
class ControllerAdvice {
    val logger: Logger = LoggerFactory.getLogger(ControllerAdvice::class.java)

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        e.printStackTrace()
        return ResponseEntity(ErrorResponse(e.message), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(WebClientAuthException::class)
    fun handleWebClientAuthException(e: WebClientAuthException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(e.message), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(WebClientException::class)
    fun handleWebClientException(e: WebClientException): ResponseEntity<ErrorResponse> {
        logger.error(e.message)
        return ResponseEntity(ErrorResponse(e.message), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
