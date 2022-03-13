package com.starter.kopring.domain.base

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    val id: Long?
    ) {

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.MIN

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedDate: LocalDateTime = LocalDateTime.MIN

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BaseEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
