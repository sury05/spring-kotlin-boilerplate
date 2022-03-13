package com.starter.kopring.security

import org.apache.commons.lang3.StringUtils
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AdminLoginProcessingFilter(loginUrl: String): AbstractAuthenticationProcessingFilter(loginUrl) {
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        if (request?.method != "POST") {
            throw AuthenticationServiceException("Authentication method not supported: " + request?.method)
        }
        val username = obtainParameter(request, "username")
        val password: String = obtainParameter(request, "password")

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw IllegalStateException("email and password can not be empty")
        }

        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
    }

    private fun obtainParameter(request: HttpServletRequest, paramName: String): String {
        return request.getParameter(paramName)?.trim() ?: ""
    }
}
