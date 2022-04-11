package com.starter.kopring.security

import com.starter.kopring.domain.User
import com.starter.kopring.enums.Role
import com.starter.kopring.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class AdminUserDetailsService: UserDetailsService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String): UserDetails {
        lateinit var updatedUser: User

        userRepository.findByName(username)?.let {
            updatedUser = userRepository.save(it)
        } ?: run {
            updatedUser = userRepository.save(User(name = username, role = Role.GUEST))
        }

        return AdminUserDetails(updatedUser.name, null, updatedUser.role)
    }
}
