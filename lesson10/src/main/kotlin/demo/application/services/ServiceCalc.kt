package demo.application.services


import org.springframework.stereotype.Component


@Component
class ServiceCalc (
    val PDN : ServicePDN,
) {
    fun calc(usedId : String ,monthlyPayment: Long) = PDN.Calc(usedId, monthlyPayment)
}