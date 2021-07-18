package com.ereyesalvarez.bianchi.application.infrastructure.rest

import com.ereyesalvarez.bianchi.domain.common.QuestionException
import com.ereyesalvarez.bianchi.domain.question.StepAggregate
import com.ereyesalvarez.bianchi.domain.question.input.GetCurrentStepAggregateForCodeUseCase
import com.ereyesalvarez.bianchi.domain.question.input.IncrementStepIndexForCodeUseCase
import com.ereyesalvarez.bianchi.domain.question.input.ValidateAnswerForCurrentQuestionByCodeUseCase
import javax.annotation.security.RolesAllowed
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.SecurityContext


@Path("api/question")
class QuestionResource(
    private val getCurrentStepAggregateForCodeUseCase: GetCurrentStepAggregateForCodeUseCase,
    private val validateAnswerForCurrentQuestionByCodeUseCase: ValidateAnswerForCurrentQuestionByCodeUseCase,
    private val incrementStepIndexForCodeUseCase: IncrementStepIndexForCodeUseCase
) {
    @GET
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCurrentStep(@Context ctx: SecurityContext): StepAggregate {
        return getCurrentStepAggregateForCodeUseCase.execute(ctx.userPrincipal.name)
    }

    @POST
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    fun sendAnswer(@Context ctx: SecurityContext, input: AnswerDto): StepAggregate {
        val code: String = ctx.userPrincipal.name
        if (validateAnswerForCurrentQuestionByCodeUseCase.execute(input.answer, code)) {
            incrementStepIndexForCodeUseCase.execute(code)
            return getCurrentStepAggregateForCodeUseCase.execute(code)
        }
        throw QuestionException("La respuesta no es correcta")
    }

}

data class AnswerDto(val answer: String)
