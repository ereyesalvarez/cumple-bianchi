package com.ereyesalvarez.bianchi.application.infrastructure.rest

import com.ereyesalvarez.bianchi.domain.common.QuestionException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider


@Provider
class QuestionExceptionHandler : ExceptionMapper<QuestionException> {
    override fun toResponse(exception: QuestionException): Response {
        return Response.status(Response.Status.BAD_REQUEST).entity(ErrorDto(exception.message)).build()
    }
}