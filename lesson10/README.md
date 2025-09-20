На этом уроке закрепляем
1) Spring bean https://www.baeldung.com/spring-bean
2) Feign client https://www.baeldung.com/spring-cloud-openfeign
3) Работаем со [swagger](https://www.baeldung.com/spring-rest-openapi-documentation)

Смотрим новое
1) Конфигурации https://docs.spring.io/spring-framework/reference/core/beans/java/configuration-annotation.html
2) docker run https://docs.docker.com/reference/cli/docker/container/run/
3) Самостоятельно создаем Feign client для приложения поднятого через `docker run --rm -p8080:8080 ghcr.io/monax111/school57project:main`. 
Делаем запрос к методу POST /search для получения номера команды

Домашнее задание
1) По сколько большинство сжульничело и получило номер команды окольными путями, мы не разобрали [Query Parameters](https://www.baeldung.com/java-httpservletrequest-get-query-parameters), [Path Variables](https://www.baeldung.com/spring-pathvariable) и [Request Headers](https://www.baeldung.com/spring-rest-http-headers). Так что с ними вам нужно разобраться дома самостоятельно.
2) Найти свою команду, выбрать лидера. Можно еще название команды придумать, только адекватное.
3) Лидеры команд, заводят новые репозитории под командный проект и добавляют в него других участников команды.
4) К следующему уроку в этой репе нужно написать свое spring приложение, которое имеет контроллер и feign клиент. Контроллер принимает на вход число, сервис увеличивает его на 1, клиент делает вызов c новым числом своего же контроллера.
5) ВАЖНО! все члены команды должны поучаствовать в создании сервиса, нужно самостоятельно распределить работу, кто-то пишет контроллер, кто-то клиент, тесты и тд.
6) С помощью плагина [docker-spring-boot-application](https://bmuschko.github.io/gradle-docker-plugin/current/user-guide/) и уже составленного файлика [build.yml](build.yml) нужно собрать докер образ вашего приложения и запушить его на github
