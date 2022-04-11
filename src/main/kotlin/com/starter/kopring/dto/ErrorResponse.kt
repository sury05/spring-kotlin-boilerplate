package com.starter.kopring.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ErrorResponse(
    @JsonProperty("result_code")
    var resultCode: String? = null,
    @JsonProperty("http_status")
    var httpStatus: String? = null,
    var message: String? = null,
    var path: String? = null,
    var timestamp: LocalDateTime? = null,
)

