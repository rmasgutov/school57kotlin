package ru.tbank.education.school.lesson6.creditriskanalyzer

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Decision
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.rules.ScoringRule

class ScoringEngine(
    private val rules: List<ScoringRule>
) {

    fun evaluate(client: Client): Pair<Decision, List<ScoringResult>> {
        val scores = rules.map { it.evaluate(client) }
        val totalScore = scores.sumOf { it.score.value }

        val decision = when {
            totalScore > 0 -> Decision.APPROVED
            totalScore == 0 -> Decision.REVIEW
            else -> Decision.REJECTED
        }

        return decision to scores
    }
}