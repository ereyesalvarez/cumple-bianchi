package com.ereyesalvarez.bianchi.domain.question.service

import com.ereyesalvarez.bianchi.domain.question.GetCurrentStepForCodePort
import com.ereyesalvarez.bianchi.domain.question.GetStepCountForCodePort
import com.ereyesalvarez.bianchi.domain.question.StepAggregate
import com.ereyesalvarez.bianchi.domain.question.input.GetCurrentStepAggregateForCodeUseCase

class GetCurrentStepAggregateForCodeService(
    private val getCurrentStepForCodePort: GetCurrentStepForCodePort,
    private val getStepCountForCodePort: GetStepCountForCodePort
    ): GetCurrentStepAggregateForCodeUseCase {
    override fun execute(code: String): StepAggregate {
        return StepAggregate(getStepCountForCodePort.execute(code), getCurrentStepForCodePort.execute(code))
    }
}