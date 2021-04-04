package com.pedrocomitto.poc.customer.router

import com.pedrocomitto.poc.customer.handler.CustomerHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CustomerRouter {

    @Bean
    fun routes(customerHandler: CustomerHandler) = coRouter {
        GET("/customers", customerHandler::findAll)
        GET("/customers/{id}", customerHandler::findById)
        POST("/customers", customerHandler::create)
        PUT("/customers/{id}", customerHandler::update)
        DELETE("customers/{id}", customerHandler::delete)
    }

}