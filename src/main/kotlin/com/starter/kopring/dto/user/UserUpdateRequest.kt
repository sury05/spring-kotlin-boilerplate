package com.starter.kopring.dto.user

import com.starter.kopring.enums.Role
import javax.validation.constraints.NotNull

data class UserUpdateRequest(
    @field:NotNull(message = "role can't be empty")
    val role: Role)
