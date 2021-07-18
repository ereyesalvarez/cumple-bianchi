package com.ereyesalvarez.bianchi.application.infrastructure.rest

import com.ereyesalvarez.bianchi.domain.common.QuestionException
import com.ereyesalvarez.bianchi.domain.question.IncrementStepNumberForCodePort
import com.ereyesalvarez.bianchi.domain.question.StepAggregate
import com.ereyesalvarez.bianchi.domain.question.StepType
import com.ereyesalvarez.bianchi.domain.question.input.GetCurrentStepAggregateForCodeUseCase
import javax.annotation.security.RolesAllowed
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.SecurityContext

@Path("api/step")
class StepResource(
    private val incrementStepNumberForCodePort: IncrementStepNumberForCodePort,
    private val getCurrentStepAggregateForCodeUseCase: GetCurrentStepAggregateForCodeUseCase
) {
    @POST
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    fun incrementStep(@Context ctx: SecurityContext, answer: String): StepAggregate {
        val code: String = ctx.userPrincipal.name
        val aggregate = getCurrentStepAggregateForCodeUseCase.execute(code)
        if (aggregate.step.type == StepType.QUESTION || aggregate.step.type == StepType.GIFT) {
            throw QuestionException("cant increment question or GIFT type")
        }
        if (aggregate.step.type!== StepType.COMPLETED){
            incrementStepNumberForCodePort.execute(code)
        }
        return getCurrentStepAggregateForCodeUseCase.execute(code)
    }
}
