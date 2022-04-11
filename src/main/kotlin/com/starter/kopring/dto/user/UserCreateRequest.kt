package com.starter.kopring.dto.user

import com.starter.kopring.domain.User
import com.starter.kopring.enums.Role
import javax.validation.constraints.NotNull

data class UserCreateRequest(
    @field:NotNull(message = "name can't be empty")
    val name: String,
    val role: Role? = Role.GUEST
) {

    fun toEntity(): User = User(name = name, role = role)
}
