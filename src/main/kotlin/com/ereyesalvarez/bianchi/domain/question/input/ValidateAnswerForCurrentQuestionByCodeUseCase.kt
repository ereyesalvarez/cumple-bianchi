package com.ereyesalvarez.bianchi.domain.question.input
interface ValidateAnswerForCurrentQuestionByCodeUseCase {
    fun execute(answer: String, code: String): Boolean
}