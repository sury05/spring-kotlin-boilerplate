package com.starter.kopring.client

import com.starter.kopring.client.dto.AccessTokenResponse
import com.starter.kopring.client.dto.AuthResponse
import com.starter.kopring.exception.WebClientAuthException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.reactive.function.client.WebClient

@Component
class AuthClientApi @Autowired constructor(val authWebClient: WebClient){

    fun getAccessToken(username: String, password: String): AccessTokenResponse? {
        val formData = LinkedMultiValueMap<String, String>()
        formData.set("username", username)
        formData.set("password", password)

        return authWebClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .retrieve()
                .onStatus(HttpStatus::is2xxSuccessful) {
                    throw WebClientAuthException("client exception")
                }
                .bodyToMono(AccessTokenResponse::class.java)
                .block()
    }

    fun authenticate(accessToken: String): AuthResponse? {
        return authWebClient.post()
                .uri("/user")
                .header("token", accessToken)
                .retrieve()
                .bodyToMono(AuthResponse::class.java)
                .block()
    }
}
