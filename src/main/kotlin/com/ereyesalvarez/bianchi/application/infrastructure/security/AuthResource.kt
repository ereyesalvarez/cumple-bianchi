package com.ereyesalvarez.bianchi.application.infrastructure.security;

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.annotation.security.PermitAll
import javax.enterprise.context.RequestScoped
import javax.ws.rs.*
import javax.ws.rs.core.MediaType


@Path("auth")
@RequestScoped
class AuthResource(
    private val securityService: SecurityService
) {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @POST
    @Path("/access")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    fun login(input: LoginDto): BearerOutDto {
        log.info("login request")
        val dto = BearerOutDto(securityService.validateCodeLogin(input.appId, input.code))
        log.info("login request success")
        return dto
    }
}
