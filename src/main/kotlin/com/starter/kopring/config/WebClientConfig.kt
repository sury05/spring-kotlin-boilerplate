package com.starter.kopring.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
class WebClientConfig {

    @Value("\${auth-service.base-url}")
    lateinit var AUTH_SERVICE_CLIENT_URL: String
    @Value("\${auth-service.client-id}")
    lateinit var AUTH_SERVICE_BASIC_AUTH_USERNAME: String
    @Value("\${auth-service.client-id}")
    lateinit var AUTH_SERVICE_BASIC_AUTH_PASSWORD: String

    @Bean
    fun authWebClient(): WebClient {
        return WebClient.builder()
                .baseUrl(AUTH_SERVICE_CLIENT_URL)
                .defaultHeaders { headers -> headers.setBasicAuth(AUTH_SERVICE_BASIC_AUTH_USERNAME, AUTH_SERVICE_BASIC_AUTH_PASSWORD)}
                .clientConnector(
                    ReactorClientHttpConnector(
                        HttpClient.create()
                            .responseTimeout(Duration.ofSeconds(3))
                    ))
                .build()
    }
}
