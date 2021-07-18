package com.ereyesalvarez.bianchi.application.infrastructure.config

import io.smallrye.config.ConfigMapping

@ConfigMapping(prefix = "com.ereyesalvarez.quarkus.jwt")
interface JWTProperties {
    fun password(): PasswordProperties
    fun duration(): Long
    interface PasswordProperties {
        fun secret(): String
        fun iteration(): Int
        fun keyLength(): Int
    }
}