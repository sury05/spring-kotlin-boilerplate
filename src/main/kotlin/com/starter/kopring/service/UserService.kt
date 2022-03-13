package com.starter.kopring.service

import com.starter.kopring.client.AuthClientApi
import com.starter.kopring.domain.User
import com.starter.kopring.enum.Role
import com.starter.kopring.exception.UserNotFoundException
import com.starter.kopring.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(val userRepository: UserRepository, val authClientApi: AuthClientApi) {
    fun getAllUser(): MutableList<User> {
        return userRepository.findAll()
    }

    fun getUser(id: Long): User? {
        return userRepository.findByIdOrNull(id)
    }

    fun createUser(user: User): User {
        return userRepository.save(user)
    }

    fun updateUserRole(id: Long, role: Role): User {
        getUser(id)?.let {
            it.role = role
            val updatedUser = userRepository.save(it)

            val auth = SecurityContextHolder.getContext().authentication
            SecurityContextHolder.getContext().authentication =
                UsernamePasswordAuthenticationToken(auth.principal, null, mutableListOf(SimpleGrantedAuthority("ROLE_$role")))

            return updatedUser
        }

        throw UserNotFoundException("user[${id}] is not found")
    }

    fun delete(id: Long) {
        getUser(id)?.run {
            userRepository.deleteById(id)
        }?: throw UserNotFoundException("user[${id}] is not found")
    }

    fun getUserFromAuthClient(username: String, password: String): String {
        val accessToken = authClientApi.getAccessToken(username, password)?.accessToken
            ?: throw UserNotFoundException("$username not found in auth server")

        return authClientApi.authenticate(accessToken)?.username
            ?: throw UserNotFoundException("$username not found in auth server")
    }
}
