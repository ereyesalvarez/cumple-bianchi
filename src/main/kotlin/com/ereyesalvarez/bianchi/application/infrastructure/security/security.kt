package com.ereyesalvarez.bianchi.application.infrastructure.security;

import com.ereyesalvarez.bianchi.application.infrastructure.mongo.CodeEntity
import com.ereyesalvarez.poverty.application.infrastructure.config.JWTProperties
import io.quarkus.security.UnauthorizedException
import io.smallrye.jwt.build.Jwt
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.jwt.Claims
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.enterprise.context.RequestScoped

data class LoginDto(val appId: String, val code: String)

data class BearerOutDto(val token: String)


@RequestScoped
class SecurityService(
    private val encoder: PBKDF2Encoder,
    private val jwtProperties: JWTProperties,
    @ConfigProperty(name = "mp.jwt.verify.issuer") private val issuer: String
) {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    fun validateCodeLogin(appId: String, code: String): String {
        if(appId != "bianchi") {
            log.info("Invalid login: Bad appId $appId")
            throw UnauthorizedException("Invalid login")
        }
        val matchCode = code.toUpperCase()
        val codeEntity = CodeEntity.findByCode(matchCode)
        if(codeEntity == null){
            log.info("Invalid login: Code not found $code")
            throw UnauthorizedException("Invalid login")
        }
        if(!codeEntity.active) {
            log.info("Invalid login: Code $code not active")
            throw UnauthorizedException("Invalid login")
        }
        val roles = mutableSetOf("USER")
        return generateToken(matchCode, roles, jwtProperties.duration())
    }


    private fun generateToken(upn: String, roles: MutableSet<String>, duration: Long): String {
        val now = System.currentTimeMillis() / 1000
        return Jwt.issuer(issuer)
            .upn(upn)
            .groups(roles)
            .claim(Claims.iat, now)
            .claim(Claims.exp, now + duration)
            .claim(Claims.email, upn)
            .sign()
    }
}


@RequestScoped
class PBKDF2Encoder(
    private val jwtProperties: JWTProperties,
) {
    /**
     * More info (https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html)
     * @param cs password
     * @return encoded password
     */
    fun encode(cs: CharSequence): String? {
        return try {
            val result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                .generateSecret(
                    PBEKeySpec(
                        cs.toString().toCharArray(),
                        jwtProperties.password().secret().toByteArray(),
                        jwtProperties.password().iteration(),
                        jwtProperties.password().keyLength()
                    )
                )
                .encoded
            Base64.getEncoder().encodeToString(result)
        } catch (ex: NoSuchAlgorithmException) {
            throw RuntimeException(ex)
        } catch (ex: InvalidKeySpecException) {
            throw RuntimeException(ex)
        }
    }
}