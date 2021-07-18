package com.ereyesalvarez.bianchi.application.adapter;

import com.ereyesalvarez.bianchi.application.infrastructure.mongo.StepEntity
import com.ereyesalvarez.bianchi.domain.question.QuestionAnswerMatchByQuestionIdPort
import com.ereyesalvarez.bianchi.domain.question.Step
import com.ereyesalvarez.bianchi.domain.question.StepType
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class  QuestionAnswerMatchByQuestionIdService: QuestionAnswerMatchByQuestionIdPort {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun execute(questionId: String, answer: String): Boolean {
        val stepEntity = StepEntity.findById(ObjectId(questionId))
        if(stepEntity == null){
            return false
        }
        if(stepEntity.answer == null){
            log.error("question $questionId without answer")
            return false
        }
        return answer.toUpperCase() == stepEntity.answer
    }
}
