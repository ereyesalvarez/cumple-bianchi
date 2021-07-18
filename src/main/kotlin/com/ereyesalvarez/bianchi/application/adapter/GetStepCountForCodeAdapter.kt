package com.ereyesalvarez.bianchi.application.adapter

import com.ereyesalvarez.bianchi.application.infrastructure.mongo.GetCodeEntityOrThrowExceptionService
import com.ereyesalvarez.bianchi.application.infrastructure.mongo.StepEntity
import com.ereyesalvarez.bianchi.domain.question.GetStepCountForCodePort
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GetStepCountForCodeAdapter(private val getCodeEntityService: GetCodeEntityOrThrowExceptionService) :
    GetStepCountForCodePort {
    override fun execute(code: String): Long {
        getCodeEntityService.execute(code)
        return StepEntity.findAllByCode(code).count()
    }
}