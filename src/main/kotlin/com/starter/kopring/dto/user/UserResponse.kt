package com.starter.kopring.dto.user

import com.fasterxml.jackson.annotation.JsonProperty
import com.starter.kopring.domain.User
import com.starter.kopring.enums.Role
import java.time.LocalDateTime

data class UserResponse(
    val id: Long? = null,
    val name: String? = null,
    val role: Role? = null,
    @JsonProperty("created_at")
    val createdDate: LocalDateTime? = null,
    @JsonProperty("updated_at")
    val updatedDate: LocalDateTime? = null
)

fun UserResponse.fromEntity(user: User): UserResponse {
    return UserResponse(
        user.id,
        user.name,
        user.role,
        user.createdDate,
        user.updatedDate
    )
}
