package com.pedrocomitto.poc.customer.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("CUSTOMERS")
data class CustomerEntity(
    @Id
    var id: Long? = null,
    var name: String,
    var document: String,
    var active: Boolean = true
)