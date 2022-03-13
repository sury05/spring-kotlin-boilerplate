package com.starter.kopring.domain

import com.starter.kopring.domain.base.BaseEntity
import javax.persistence.Entity

@Entity
class Whitelist(id: Long?, host: String, port: Int): BaseEntity(id) {
    constructor(host: String, port: Int) : this(null, host, port)
}
