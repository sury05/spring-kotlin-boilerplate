package com.starter.kopring.domain

import com.starter.kopring.domain.base.BaseEntity
import com.starter.kopring.enums.Role
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class User(
    id: Long? = null,

    @Column(nullable = false)
    var name: String?,

    @Column(nullable = false)
    var role: Role?): BaseEntity(id)
