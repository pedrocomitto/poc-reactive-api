package com.pedrocomitto.poc.customer.mapper

import com.pedrocomitto.poc.customer.domain.entity.CustomerEntity
import com.pedrocomitto.poc.customer.domain.request.CustomerRequest
import com.pedrocomitto.poc.customer.domain.response.CustomerResponse

fun CustomerRequest.toEntity() =
    CustomerEntity(
        name = name,
        document = document
    )

fun CustomerEntity.updateFrom(customerRequest: CustomerRequest): CustomerEntity {
    this.name = customerRequest.name
    this.document = customerRequest.document

    return this
}

fun CustomerEntity.toResponse() =
    CustomerResponse(
        id = id,
        name = name,
        document = document
    )