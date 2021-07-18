package com.ereyesalvarez.bianchi.application.adapter

import com.ereyesalvarez.bianchi.application.infrastructure.mongo.CodeEntity
import com.ereyesalvarez.bianchi.application.infrastructure.mongo.GetCodeEntityOrThrowExceptionService
import com.ereyesalvarez.bianchi.application.infrastructure.mongo.StepEntity
import com.ereyesalvarez.bianchi.domain.common.QuestionException
import com.ereyesalvarez.bianchi.domain.question.GetStepCountForCodePort
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GetStepCountForCodeAdapter(private val getCodeEntityService: GetCodeEntityOrThrowExceptionService): GetStepCountForCodePort {
    override fun execute(code: String): Long {
        getCodeEntityService.execute(code)
        return StepEntity.findAllByCode(code).count()
    }
}