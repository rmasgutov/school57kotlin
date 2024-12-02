package demo.application.client

import demo.application.dto.User
import feign.Headers
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Profile("!dev")
@Component
// клиент для получения клиентов в корпоративное CRM по id
@FeignClient(name = "crmClient", url = "http://localhost:8080/")
interface CrmClient: UserGetter {
    @RequestMapping(method = [RequestMethod.GET], value = ["users/{userId}"])
    @Headers("Content-Type: application/json")
    override fun getUserById(@PathVariable userId: String): User {
        TODO()
    }

}