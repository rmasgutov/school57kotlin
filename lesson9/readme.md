На этом уроке изучали 
1) [Spring DI](https://www.baeldung.com/spring-dependency-injection) Посмотрели еще раз ка ксоздавать бины и как из инжектить в другие бины через конструктор
2) Из чего стоит build.gradle.kts: [plugins](https://docs.gradle.org/current/userguide/plugins.html), [dependencies](https://docs.gradle.org/current/userguide/declaring_dependencies.html), [BOM](https://docs.gradle.org/current/userguide/platforms.html)
3) Посмотрели как выглядит [swagger](https://www.baeldung.com/spring-rest-openapi-documentation)

Домашнее задание:
1) Проверить что у вас стоит docker. Можно поставить [docker-desktop](https://www.docker.com/products/docker-desktop/). Критерий успеха - должна отрабатывать команда в консоли `docker run --rm hello-world`
2) Пройти [тест](https://psytests.org/work/btrspi-run.html) и скинуть результат в телеграм чат
3) Самостоятельно подключить swagger по [гайду](https://www.baeldung.com/spring-rest-openapi-documentation), Сделать пару вызовов метода score через UI
4) Подумать над тем какие данные вам нужны для принятия решения о выдаче кредита и продумать логику `LogicService`
5) Написать тесты на логику в классе `DemoApplicationTests`
6) Закоммитить изменения, создать мр в основную репу, проверить что пайплайн прошел(зеленая галка).