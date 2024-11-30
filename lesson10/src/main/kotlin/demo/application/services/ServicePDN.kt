package demo.application.services

import demo.application.client.CrmClient
import demo.application.client.SyntheticUserGenerator
import demo.application.dto.User
import org.springframework.stereotype.Component


@Component
class ServicePDN (
    val crmClient : CrmClient,
    val syntheticUserGenerator: SyntheticUserGenerator,
){
    fun Calc(userId: String, monthlyPayment: Long): Long {
        val user: User = if(System.getenv("environment") == "Test"){
            crmClient.getUserById(userId)
        }else{
            syntheticUserGenerator.generateUser()
        }

        val existMonthlyPayment = user.loans.filterNot {
            it.isClose
        }.sumOf { it.monthlyPayment }

        val totalMonthlyPayment = existMonthlyPayment + monthlyPayment

        return user.income / 3 - totalMonthlyPayment;
    }
}