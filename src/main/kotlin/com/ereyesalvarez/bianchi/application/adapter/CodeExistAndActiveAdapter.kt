package com.ereyesalvarez.bianchi.application.adapter

import com.ereyesalvarez.bianchi.application.infrastructure.mongo.CodeEntity
import com.ereyesalvarez.bianchi.domain.question.CodeExistAndActivePort
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CodeExistAndActiveAdapter: CodeExistAndActivePort {
    override fun execute(code: String): Boolean {
        val codeEntity = CodeEntity.findByCode(code);
        return codeEntity != null && codeEntity.active
    }
}