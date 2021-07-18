package com.ereyesalvarez.bianchi.domain.question.service

import com.ereyesalvarez.bianchi.domain.question.QuestionAnswerMatchByQuestionIdPort
import com.ereyesalvarez.bianchi.domain.question.StepType
import com.ereyesalvarez.bianchi.domain.question.input.GetCurrentStepAggregateForCodeUseCase
import com.ereyesalvarez.bianchi.domain.question.input.ValidateAnswerForCurrentQuestionByCodeUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ValidateAnswerForCurrentQuestionByCodeService(private val getCurrentStepAggregateForCodeUseCase: GetCurrentStepAggregateForCodeUseCase, private val questionAnswerMatchByQuestionIdPort: QuestionAnswerMatchByQuestionIdPort): ValidateAnswerForCurrentQuestionByCodeUseCase {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun execute(answer: String, code: String): Boolean {
        val aggregateStep = getCurrentStepAggregateForCodeUseCase.execute(code)
        if(aggregateStep.step.type != StepType.QUESTION){
            log.error("try to validate question for: ${aggregateStep.step.index} by $code and is ${aggregateStep.step.type}")
            return false
        }
        val questionId = aggregateStep.step.question?.id
        if(questionId == null){
            log.error("try to validate question for: ${aggregateStep.step.index} by $code and is empty")
            return false
        }
        return questionAnswerMatchByQuestionIdPort.execute(questionId, answer)
    }
}