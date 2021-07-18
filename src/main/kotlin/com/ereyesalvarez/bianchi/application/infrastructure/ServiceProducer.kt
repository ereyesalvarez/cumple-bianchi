package com.ereyesalvarez.bianchi.application.infrastructure

import com.ereyesalvarez.bianchi.domain.question.GetCurrentStepForCodePort
import com.ereyesalvarez.bianchi.domain.question.GetStepCountForCodePort
import com.ereyesalvarez.bianchi.domain.question.IncrementStepNumberForCodePort
import com.ereyesalvarez.bianchi.domain.question.QuestionAnswerMatchByQuestionIdPort
import com.ereyesalvarez.bianchi.domain.question.service.GetCurrentStepAggregateForCodeService
import com.ereyesalvarez.bianchi.domain.question.service.IncrementStepIndexForCodeService
import com.ereyesalvarez.bianchi.domain.question.service.ValidateAnswerForCurrentQuestionByCodeService
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.Produces

class ServiceProducer(
    private val getCurrentStepForCodePort: GetCurrentStepForCodePort,
    private val getStepCountForCodePort: GetStepCountForCodePort,
    private val incrementStepNumberForCodePort: IncrementStepNumberForCodePort,
    private val questionAnswerMatchByQuestionIdPort: QuestionAnswerMatchByQuestionIdPort
) {
    @Produces
    @ApplicationScoped
    fun getCurrentStepAggregateForCodeService(): GetCurrentStepAggregateForCodeService =
        GetCurrentStepAggregateForCodeService(getCurrentStepForCodePort, getStepCountForCodePort)

    @Produces
    @ApplicationScoped
    fun incrementStepIndexForCodeService(): IncrementStepIndexForCodeService =
        IncrementStepIndexForCodeService(incrementStepNumberForCodePort)

    @Produces
    @ApplicationScoped
    fun validateAnswerForCurrentQuestionByCodeService(): ValidateAnswerForCurrentQuestionByCodeService =
        ValidateAnswerForCurrentQuestionByCodeService(
            getCurrentStepAggregateForCodeService(),
            questionAnswerMatchByQuestionIdPort
        )

}