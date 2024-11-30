package demo.application.client

import demo.application.dto.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

// клиент для получения клиентов в корпоративное CRM по id
@FeignClient(name = "crmClient", url = "http://localhost:8080/")
interface CrmClient {

    @GetMapping(value = ["/users/{userId}"])
    fun getUserById(@PathVariable userId: String): User

}