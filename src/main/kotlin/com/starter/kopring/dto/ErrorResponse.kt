package com.starter.kopring.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorResponse(
    @JsonProperty("message")
    val message: String?
    )
