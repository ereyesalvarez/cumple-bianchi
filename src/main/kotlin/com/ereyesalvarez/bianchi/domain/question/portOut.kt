package com.ereyesalvarez.bianchi.domain.question

import com.ereyesalvarez.bianchi.domain.question.Step

interface GetCurrentStepForCodePort{
    fun execute(code: String): Step
}

interface GetStepCountForCodePort{
    fun execute(code: String): Long
}

interface IncrementStepNumberForCodePort{
    fun execute(code: String)
}

interface CodeExistAndActivePort{
    fun execute(code: String): Boolean
}

interface QuestionAnswerMatchByQuestionIdPort{
    fun execute(questionId: String, answer: String): Boolean
}