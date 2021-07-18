package com.ereyesalvarez.bianchi.domain.question

enum class StepType {
    QUESTION, FACT, GIFT, COMPLETED
}

data class Step(val index: Int, val type: StepType, var question: Question? = null, var fact: Fact? = null)

enum class QuestionType {
    DATE, TEXT, OPTION
}

data class Question(
    val id: String, val type: QuestionType, val text: String,
    val hint: String?, val options: Set<Option>?
)

data class Option(val value: String, val text: String)

data class Fact(val id: String, val text: String, val text2: String?, val img: String?)

data class Gift(val id: String, val text: String, val text2: String?, val img: String?)