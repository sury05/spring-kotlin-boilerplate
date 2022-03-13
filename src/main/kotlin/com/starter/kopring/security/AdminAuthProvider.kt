package com.starter.kopring.security

import com.starter.kopring.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

class AdminAuthProvider constructor(
    private val adminUserDetailsService: AdminUserDetailsService
): AuthenticationProvider {

    @Autowired
    lateinit var userService: UserService

    override fun authenticate(authentication: Authentication): Authentication {
//        val username = userService.getUserFromAuthClient(
//                            authentication.principal as String,
//                            authentication.credentials as String)

        val userDetails = adminUserDetailsService.loadUserByUsername(authentication.principal as String)

        return UsernamePasswordAuthenticationToken(userDetails.username, null, userDetails.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}
