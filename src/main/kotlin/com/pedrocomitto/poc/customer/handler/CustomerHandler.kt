package com.pedrocomitto.poc.customer.handler

import com.pedrocomitto.poc.customer.domain.request.CustomerRequest
import com.pedrocomitto.poc.customer.exception.CustomerNotFoundException
import com.pedrocomitto.poc.customer.mapper.toEntity
import com.pedrocomitto.poc.customer.mapper.toResponse
import com.pedrocomitto.poc.customer.mapper.updateFrom
import com.pedrocomitto.poc.customer.repository.CustomerRepository
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class CustomerHandler(
    private val repository: CustomerRepository
) {

    suspend fun findAll(request: ServerRequest) =
        ServerResponse.ok()
            .bodyAndAwait(
                repository.findAll()
                    .map { it.toResponse() }
            )

    suspend fun findById(request: ServerRequest) =
        ServerResponse.ok()
            .bodyValueAndAwait(
                repository.findById(
                    request.pathVariable("id").toLong()
                )?.toResponse()
                    ?: throw CustomerNotFoundException()
            )

    suspend fun create(request: ServerRequest) =
        repository.save(
            request.awaitBody(CustomerRequest::class)
                .toEntity()
        ).let {
            ServerResponse.created(request.uri()).buildAndAwait()
        }

    suspend fun update(request: ServerRequest): ServerResponse {
        repository.findById(request.pathVariable("id").toLong())
            ?.updateFrom(request.awaitBody(CustomerRequest::class))
            ?.let { repository.save(it) }
            ?: throw CustomerNotFoundException()

        return ServerResponse.noContent().buildAndAwait()
    }

    suspend fun delete(request: ServerRequest): ServerResponse {
        repository.findById(request.pathVariable("id").toLong())?.let {
            it.active = false
            repository.save(it)
        } ?: throw CustomerNotFoundException()

        return ServerResponse.noContent().buildAndAwait()
    }

}
