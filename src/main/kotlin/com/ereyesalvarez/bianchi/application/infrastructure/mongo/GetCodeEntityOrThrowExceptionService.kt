package com.ereyesalvarez.bianchi.application.infrastructure.mongo

import com.ereyesalvarez.bianchi.domain.common.QuestionException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GetCodeEntityOrThrowExceptionService {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    fun execute(code: String): CodeEntity{
        val codeEntity = CodeEntity.findByCode(code)
        if (codeEntity == null || !codeEntity.active) {
            log.error("code $code not found or not active")
            throw QuestionException("code not found or not active")
        }
        return codeEntity
    }
}