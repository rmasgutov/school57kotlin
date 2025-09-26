package ru.tbank.education.school.lesson3
import ru.tbank.education.school.lesson3.fitness.club.Club
import ru.tbank.education.school.lesson3.fitness.club.models.Available

/*1. Создайте минимум **3–4 осмысленных класса**.
2. Используйте как **основной**, так и хотя бы один **дополнительный конструктор**.
3. Реализуйте **наследование**: должен быть как минимум один базовый класс и несколько классов-наследников.
4. Используйте ключевые слова: `open`, `override`, `super`.
5. Создайте хотя бы один **абстрактный класс**, который определяет общие характеристики.
6. У некоторых свойств должен быть модификатор доступа `private` или `protected`.
7. Реализуйте хотя бы один **кастомный геттер или сеттер**.
8. Покажите пример **ассоциации**: хотя бы один класс должен содержать ссылку на другой.
    - Используйте **агрегацию** или **композицию** (на ваш выбор).
9. Используйте хотя бы один **data class** для хранения данных.
10. *(опционально)* Создайте хотя бы один **sealed class**, описывающий ограниченное множество состояний или событий.
11. Продемонстрируйте **сценарий взаимодействия** с вашей системой в функции `main`.

---

## Результат
- Код программы с выбранной темой.
- Функция `main()`, в которой создаются объекты и демонстрируется взаимодействие между ними.
 */
fun main() {
    val club = Club(name = "Фитнесс клуб")
    val customer = club.registerCustomer(fullName = "Никита")
    val trainer = club.registerTrainer(fullName = "Антон")
    val GymMembership = club.openAvail(customer, work = false)
    val Money = club.openMoney(customer, money=1000)

    club.available(to = GymMembership, work = false, decription = "Абонимент")
    club.money(from = Money, amount = 500, decription = "Пополнение")

    GymMembership.printReport()


}