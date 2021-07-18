package com.ereyesalvarez.bianchi.domain.question.input

import com.ereyesalvarez.bianchi.domain.question.StepAggregate

interface GetCurrentStepAggregateForCodeUseCase{
    fun execute(code: String): StepAggregate
}