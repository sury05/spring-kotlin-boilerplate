package com.starter.kopring.utils

import org.springframework.security.core.context.SecurityContextHolder

class UserUtil {
    companion object {
        fun getCurrentUser(): String? {
            return SecurityContextHolder.getContext().authentication.name
        }
    }
}
