package com.starter.kopring.service

import com.starter.kopring.domain.Whitelist
import com.starter.kopring.repository.WhitelistRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WhitelistService @Autowired constructor(val whitelistRepository: WhitelistRepository) {
    fun getAllWhitelist(): MutableList<Whitelist> {
        return whitelistRepository.findAll()
    }

    fun createWhitelist(whitelist: Whitelist): Whitelist {
        return whitelistRepository.save(whitelist)
    }
}
