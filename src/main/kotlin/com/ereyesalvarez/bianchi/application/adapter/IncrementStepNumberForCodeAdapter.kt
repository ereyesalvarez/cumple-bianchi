package com.ereyesalvarez.bianchi.application.adapter

import com.ereyesalvarez.bianchi.application.infrastructure.mongo.GetCodeEntityOrThrowExceptionService
import com.ereyesalvarez.bianchi.domain.question.IncrementStepNumberForCodePort
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class IncrementStepNumberForCodeAdapter(private val getCodeEntityService: GetCodeEntityOrThrowExceptionService) :
    IncrementStepNumberForCodePort {

    override fun execute(code: String) {
        val codeEntity = getCodeEntityService.execute(code)
        codeEntity.currentStepIndex++
        codeEntity.update()
    }
}