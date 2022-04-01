package com.starter.kopring.exception.handler

import com.starter.kopring.dto.ErrorResponse
import com.starter.kopring.exception.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.reactive.function.client.WebClientException
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {
    private val logger: Logger = LoggerFactory.getLogger(ControllerAdvice::class.java)

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInternalServerError(e: Exception, request: HttpServletRequest): ErrorResponse {
        logging(e, request)
        return toErrorResponse(InternalServerErrorException(), request)
    }

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(e: BaseException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        logging(e, request)
        return ResponseEntity(toErrorResponse(e, request), e.getHttpStatus())
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(e: MethodArgumentNotValidException, request: HttpServletRequest): ErrorResponse {
        logging(e, request)
        val errorMessage = e.bindingResult.fieldErrors.map {
            "${it.field} : ${it.defaultMessage}"
        }.joinToString { ", " }

        return toErrorResponse(BadRequestException(errorMessage, LogLevel.INFO), request)
    }


    @ExceptionHandler(WebClientAuthException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleWebClientAuthException(e: WebClientAuthException, request: HttpServletRequest): ErrorResponse {
        logging(e, request)
        return toErrorResponse(e, request)
    }

    @ExceptionHandler(WebClientException::class)
    fun handleWebClientException(e: WebClientException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        logging(e, request)
        return ResponseEntity(
            ErrorResponse(request.requestURI, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.localizedMessage),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    private fun logging(exception: Exception, request: HttpServletRequest) {
        val loggingText = "[PATH] ${request.requestURI} [Exception] ${exception.javaClass.simpleName}"

        if (exception !is BaseException) {
            logger.error(loggingText, exception)
            return
        }

        val baseException: BaseException = exception
        when (baseException.logLevel) {
            LogLevel.ERROR -> logger.error(loggingText, exception)
            LogLevel.WARN -> logger.warn(loggingText, exception)
            LogLevel.INFO -> logger.info(loggingText, exception)
            LogLevel.DEBUG -> logger.debug(loggingText, exception)
            else -> {}
        }
    }

    private fun toErrorResponse(exception: BaseException, request: HttpServletRequest): ErrorResponse {
        return ErrorResponse(
            request.requestURI,
            exception.getHttpStatus().value(),
            exception.localizedMessage
        )
    }
}
