package com.starter.kopring.controller

import com.starter.kopring.domain.Whitelist
import com.starter.kopring.dto.WhitelistRequest
import com.starter.kopring.service.WhitelistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/whitelist")
class WhitelistController @Autowired constructor(val whitelistService: WhitelistService) {

    @GetMapping
    fun getAll(): ResponseEntity<MutableList<Whitelist>> {
        return ResponseEntity.ok(whitelistService.getAllWhitelist())
    }

    @PostMapping
    fun create(@RequestBody request: WhitelistRequest): ResponseEntity<Whitelist> {
        return ResponseEntity.ok(whitelistService.createWhitelist(request.toEntity()))
    }
}
