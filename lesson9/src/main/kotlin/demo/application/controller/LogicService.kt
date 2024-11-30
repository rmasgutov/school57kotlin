package demo.application.controller
import org.springframework.stereotype.Component
import java.time.LocalDateTime
@Component
class LogicService {
    fun simpleScore(creditApplication: CreditApplication): Boolean {
        if (creditApplication.user.age <  18) {
            return false
        }

        if(creditApplication.user.income < creditApplication.monthlyPayment){
            return false
        }
        if(creditApplication.user.loans.filter{it.loanTerm<LocalDateTime.now()&&!it.isClose}.size>0){
            return false
        }
        if(creditApplication.user.creditScore<600){
            return false
        }
        return creditApplication.user.loans.filter {
            it.isClose
        }.sumOf { it.monthlyPayment } + creditApplication.monthlyPayment < creditApplication.user.income / 3
    }
}