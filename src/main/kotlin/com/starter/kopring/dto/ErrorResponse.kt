package com.starter.kopring.dto

data class ErrorResponse(
    val path: String,
    val statusCode: Int,
    val message: String
)

