package com.starter.kopring.dto

import com.starter.kopring.domain.Whitelist

data class WhitelistRequest(val host: String, val port: Int) {
    fun toEntity(): Whitelist {
        return Whitelist(host = host, port = port)
    }
}
