package ru.tbank.education.school.lesson6.creditriskanalyzer

interface ScoringRule {
    val ruleName: String
    fun evaluate(client: Client): ScoringResult
}

data class ScoringResult(
    val ruleName: String,
    val score: PaymentRisk
)

enum class PaymentRisk {
    HIGH, MEDIUM, LOW
}

class AccountActivityRule(private val accr: AccountRepository, private val tr: TransactionRepository) : ScoringRule {
    override val ruleName: String = "AccountActivityRule"
    override fun evaluate(client: Client): ScoringResult {
        val accs = accr.getAccounts(client.id)
        if (accs.isEmpty()) {
            return ScoringResult(ruleName, PaymentRisk.HIGH)
        }
        var aacc = 0
        for (acc in accs) {
            val ts = tr.getTransactions(acc.id)
            var ht = false
            for (t in ts) {
                ht = true
                break
            }
            if (ht) {
                aacc++
            }
        }
        if (aacc == 0) {
            return ScoringResult(ruleName, PaymentRisk.HIGH)
        } else if (aacc >= 2) {
            return ScoringResult(ruleName, PaymentRisk.LOW)
        } else {
            return ScoringResult(ruleName, PaymentRisk.MEDIUM)
        }
    }
}

class CreditHistoryRule(private val lr: LoanRepository, private val or: OverdueRepository) : ScoringRule {
    override val ruleName: String = "CreditHistoryRule"
    override fun evaluate(client: Client): ScoringResult {
        val ls = lr.getLoans(client.id)
        if (ls.isEmpty()) {
            return ScoringResult(ruleName, PaymentRisk.LOW)
        }
        var pls = 0
        var ho = false
        for (l in ls) {
            if (l.status == LoanStatus.PAID) {
                pls++
            }
            val os = or.getOverdues(l.id)
            for (o in os) {
                if (!o.paid) {
                    ho = true
                    break
                }
            }
        }
        if (ho) {
            return ScoringResult(ruleName, PaymentRisk.HIGH)
        } else if (pls == ls.size) {
            return ScoringResult(ruleName, PaymentRisk.LOW)
        } else {
            return ScoringResult(ruleName, PaymentRisk.MEDIUM)
        }
    }
}

class SupportTicketsRule(private val tr: TicketRepository) : ScoringRule {
    override val ruleName: String = "SupportTicketsRule"
    override fun evaluate(client: Client): ScoringResult {
        val ts = tr.getTickets(client.id)
        if (ts.isEmpty()) {
            return ScoringResult(ruleName, PaymentRisk.LOW)
        }
        var cco = 0
        for (t in ts) {
            if (t.type == TicketType.COMPLAINT) {
                cco++
            }
        }
        if (cco >= 2) {
            return ScoringResult(ruleName, PaymentRisk.HIGH)
        } else if (cco == 1) {
            return ScoringResult(ruleName, PaymentRisk.MEDIUM)
        } else {
            return ScoringResult(ruleName, PaymentRisk.LOW)
        }
    }
}

class IncomeStabilityRule(private val accrr: AccountRepository, private val tr: TransactionRepository
) : ScoringRule {
    override val ruleName: String = "IncomeStabilityRule"
    override fun evaluate(client: Client): ScoringResult {
        val accs = accr.getAccounts(client.id)
        var ic = 0
        for (acc in accs) {
            val ts = tr.getTransactions(acc.id)
            for (t in ts) {
                if (t.amo > 0) {
                    ic++
                }
            }
        }
        if (ic == 0) {
            return ScoringResult(ruleName, PaymentRisk.HIGH)
        } else if (ic >= 10) {
            return ScoringResult(ruleName, PaymentRisk.LOW)
        } else if (ic >= 5) {
            return ScoringResult(ruleName, PaymentRisk.MEDIUM)
        } else {
            return ScoringResult(ruleName, PaymentRisk.HIGH)
        }
    }
}

class RegionRiskRule : ScoringRule {
    override val ruleName: String = "RegionRiskRule"
    override fun evaluate(client: Client): ScoringResult {
        when (client.region) {
            "Moscow", "Saint Petersburg" -> return ScoringResult(ruleName, PaymentRisk.LOW)
            "Kazan", "Novosibirsk" -> return ScoringResult(ruleName, PaymentRisk.MEDIUM)
            else -> return ScoringResult(ruleName, PaymentRisk.HIGH)
        }
    }
}
fun main() {
}

