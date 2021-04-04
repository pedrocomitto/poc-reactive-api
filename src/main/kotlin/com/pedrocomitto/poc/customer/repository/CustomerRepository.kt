package com.pedrocomitto.poc.customer.repository

import com.pedrocomitto.poc.customer.domain.entity.CustomerEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : CoroutineCrudRepository<CustomerEntity, Long>