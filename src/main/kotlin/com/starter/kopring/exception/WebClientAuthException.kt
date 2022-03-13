package com.starter.kopring.exception

class WebClientAuthException(private val errorDescription: String): RuntimeException(errorDescription)
