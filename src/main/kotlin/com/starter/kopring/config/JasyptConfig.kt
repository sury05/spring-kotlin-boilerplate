package com.starter.kopring.config

import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JasyptConfig(
    @Value("\${jasypt.encryptor.password}")
    val password: String
) {
    @Bean("jasyptStringEncryptor")
    fun stringEncryptor(): StringEncryptor? {
        val config = SimpleStringPBEConfig()
        config.password = password
        config.poolSize = 1

        val encryptor = PooledPBEStringEncryptor();
        encryptor.setConfig(config)

        return encryptor
    }
}
