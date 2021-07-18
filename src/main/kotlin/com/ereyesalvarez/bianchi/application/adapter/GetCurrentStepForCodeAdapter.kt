package com.ereyesalvarez.bianchi.application.adapter

import com.ereyesalvarez.bianchi.application.infrastructure.mongo.GetCodeEntityOrThrowExceptionService
import com.ereyesalvarez.bianchi.application.infrastructure.mongo.StepEntity
import com.ereyesalvarez.bianchi.application.infrastructure.mongo.toDomain
import com.ereyesalvarez.bianchi.domain.common.QuestionException
import com.ereyesalvarez.bianchi.domain.question.GetCurrentStepForCodePort
import com.ereyesalvarez.bianchi.domain.question.Step
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GetCurrentStepForCodeAdapter(private val getCodeEntityService: GetCodeEntityOrThrowExceptionService) :
    GetCurrentStepForCodePort {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun execute(code: String): Step {
        val codeEntity = getCodeEntityService.execute(code)
        val codeList = StepEntity.findAllByCode(code)
        val stepEntity = codeList.stream().filter { it.index == codeEntity.currentStepIndex }.findFirst()
        if (stepEntity.isPresent) {
            return stepEntity.get().toDomain()
        } else {
            log.error("code $code don't match index with stepList or List is empty")
            throw QuestionException("code don't match index with stepList")
        }
    }
}