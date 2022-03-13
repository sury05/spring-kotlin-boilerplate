package com.starter.kopring.repository

import com.starter.kopring.domain.Whitelist
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WhitelistRepository: JpaRepository<Whitelist, Long>
