package demo.application.service

import demo.application.dto.Loan
import org.springframework.stereotype.Service

@Service
class PdnService {

    fun getPdn(
        loans: List<Loan>,
    ): Long {
        val existMonthlyPayment = loans.filterNot {
            it.isClose
        }.sumOf { it.monthlyPayment }

        return existMonthlyPayment
    }


    fun getAdditionPdn(
        loans: List<Loan>,
        income: Long,
    ): Long {
        return income / 3 - getPdn(loans)
    }


}