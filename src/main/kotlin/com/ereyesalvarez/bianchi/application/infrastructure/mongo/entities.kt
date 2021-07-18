package com.ereyesalvarez.bianchi.application.infrastructure.mongo

import io.quarkus.mongodb.panache.MongoEntity
import io.quarkus.mongodb.panache.kotlin.PanacheMongoCompanion
import io.quarkus.mongodb.panache.kotlin.PanacheMongoEntity

@MongoEntity(collection = "steps")
class StepEntity : PanacheMongoEntity() {
    companion object : PanacheMongoCompanion<StepEntity> {
        fun findAllByCode(code: String) = find("code", code)
    }

    var index: Int? = null
    var type: String? = null
    var code: String? = null
    var questionType: String? = null
    var text: String? = null
    var hint: String? = null
    var options: MutableSet<OptionEntity>? = null
    var answer: String? = null
    var text2: String? = null
    var img: String? = null
    var attempts: Int? = 0
}

class OptionEntity(
    var value: String? = null,
    var text: String? = null
    )

@MongoEntity(collection = "codes")
class CodeEntity: PanacheMongoEntity() {
    companion object : PanacheMongoCompanion<CodeEntity> {
        fun findByCode(code: String) = CodeEntity.find("code", code).firstResult()
    }
    var code: String? = null
    var currentStepIndex = 0
    var active = true
}

