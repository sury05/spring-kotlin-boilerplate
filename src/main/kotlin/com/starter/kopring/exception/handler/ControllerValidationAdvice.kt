package com.starter.kopring.exception.handler

import com.starter.kopring.dto.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ControllerValidationAdvice {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(e: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errors: StringBuilder = StringBuilder()

        e.bindingResult.allErrors.forEach { errorObject ->
            val field = (errorObject as FieldError).field
            val message = errorObject.defaultMessage
            val value = errorObject.rejectedValue
            errors.append("\n $field $message $value")
        }

        return ResponseEntity.badRequest().body(ErrorResponse(errors.toString()))
    }
}
