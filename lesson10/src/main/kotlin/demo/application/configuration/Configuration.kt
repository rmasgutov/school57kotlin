package demo.application.configuration

import demo.application.client.SyntheticUserGenerator
import demo.application.client.UserGetter
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {

    //@Bean
    fun createUserGetter(): UserGetter {
        if (System.getenv("") == "") {
            return SyntheticUserGenerator()
        } else {
            return TODO()
        }
    }


}