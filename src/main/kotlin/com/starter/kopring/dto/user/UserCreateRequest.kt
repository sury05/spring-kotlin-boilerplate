package com.starter.kopring.dto.user

import com.starter.kopring.domain.User
import com.starter.kopring.enum.Role
import javax.validation.constraints.NotNull

data class UserCreateRequest(
    @NotNull(message = "name can't be empty")
    val name: String,
    val role: Role? = Role.GUEST) {

    fun toEntity(): User = User(name = name, role = role)
}
