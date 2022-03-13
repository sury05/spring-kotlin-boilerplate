package com.starter.kopring.controller

import com.starter.kopring.dto.user.UserCreateRequest
import com.starter.kopring.dto.user.UserResponse
import com.starter.kopring.dto.user.UserUpdateRequest
import com.starter.kopring.dto.user.fromEntity
import com.starter.kopring.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/api/users")
class UserController @Autowired constructor(val userService: UserService) {

    @GetMapping
    fun getAll(): ResponseEntity<Map<String, List<UserResponse>>> {
        val users = userService.getAllUser()
            .map {
                UserResponse().fromEntity(it)
            }
        return ResponseEntity.ok(mapOf("users" to users))
    }

    @PostMapping
    fun create(@RequestBody @Valid userRequest: UserCreateRequest): ResponseEntity<UserResponse> {
        val user = userService.createUser(userRequest.toEntity())
        return ResponseEntity.ok(UserResponse().fromEntity(user))
    }

    @PutMapping
    fun updateUserRole(@RequestParam id: Long, @RequestBody @Valid userRequest: UserUpdateRequest): ResponseEntity<UserResponse> {
        val user = userService.updateUserRole(id, userRequest.role)
        return ResponseEntity.ok(UserResponse().fromEntity(user))
    }

    @DeleteMapping
    fun delete(@RequestParam id: Long): ResponseEntity<Map<String, Long>> {
        userService.delete(id)
        return ResponseEntity.ok(mapOf("id" to id))
    }
}
