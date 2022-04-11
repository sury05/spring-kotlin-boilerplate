package com.starter.kopring

import org.assertj.core.api.Assertions.assertThat
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.junit.jupiter.api.Test

class JasyptTest {

    @Test
    internal fun create() {
        val pbeEnc = StandardPBEStringEncryptor()
        pbeEnc.setAlgorithm("PBEWithMD5AndDES")
        pbeEnc.setPassword("jasypt-key");

        assertThat(pbeEnc.encrypt("sa")).isEqualTo("");
    }
}
