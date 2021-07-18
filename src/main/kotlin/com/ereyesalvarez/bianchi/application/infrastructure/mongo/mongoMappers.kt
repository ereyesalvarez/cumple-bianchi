package com.ereyesalvarez.bianchi.application.infrastructure.mongo

import com.ereyesalvarez.bianchi.domain.common.QuestionException
import com.ereyesalvarez.bianchi.domain.question.*

fun StepEntity.toDomain(): Step {
    val stepType = StepType.valueOf(type ?: throw QuestionException("error at mapping"))
    var question: Question? = null
    var fact: Fact? = null
    if (stepType == StepType.QUESTION || stepType == StepType.GIFT) {
        val questionType = QuestionType.valueOf(questionType ?: throw QuestionException("error at mapping"))
        var domainOptions: Set<Option>? = null
        if (questionType == QuestionType.OPTION) {
            domainOptions = options?.map { Option(it.value ?: "", it.text ?: "") }?.toSet()
        }
        question = Question(
            id.toString(),
            questionType,
            text ?: throw QuestionException("error at mapping"),
            hint,
            domainOptions
        )
    }
    if (stepType == StepType.FACT || stepType == StepType.COMPLETED) {
        fact = Fact(
            id.toString(),
            text ?: throw QuestionException("error at mapping"),
            text2 ?: throw QuestionException("error at mapping"),
            img ?: throw QuestionException("error at mapping")
        )
    }
    return Step(
        index ?: throw QuestionException("error at mapping"),
        stepType,
        question,
        fact
    )
}