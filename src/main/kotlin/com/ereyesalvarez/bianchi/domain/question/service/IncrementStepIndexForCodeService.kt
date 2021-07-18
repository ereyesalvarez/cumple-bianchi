package com.ereyesalvarez.bianchi.domain.question.service

import com.ereyesalvarez.bianchi.domain.question.IncrementStepNumberForCodePort
import com.ereyesalvarez.bianchi.domain.question.input.IncrementStepIndexForCodeUseCase

class IncrementStepIndexForCodeService(private val incrementStepNumberForCodePort: IncrementStepNumberForCodePort): IncrementStepIndexForCodeUseCase {
    override fun execute(code: String) {
        incrementStepNumberForCodePort.execute(code)
    }
}