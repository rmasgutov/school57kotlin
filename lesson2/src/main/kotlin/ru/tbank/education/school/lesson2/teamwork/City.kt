package ru.tbank.education.school.lesson2.teamwork

// Данные по номерам домов : Город -> Округ -> Район -> Улица -> Номера домов
val data: Map<String, Map<String, Map<String, Map<String, Set<Int>>>>> = mapOf(
    "Москва" to mapOf(
        "CAO" to mapOf(
            "Войковский" to mapOf(
                "Зои и Александра" to setOf(1, 2, 3, 4, 6, 8),
            ),
            "Красноармейский" to mapOf(
                "Космонавт Волков" to setOf(1, 3, 5, 6, 7),
                "Ракета" to setOf(2, 4, 8),
                "Тупик" to setOf(4)
            ),
        ),
        "ЗAO" to mapOf(
            "Звенигородскй" to mapOf(
                "Шоссе" to setOf(3, 4, 5, 6, 7),
                "Славянский бульвар" to setOf(1),
            ),
            "Минский" to mapOf(
                "Шоссе" to setOf(4, 1, 3, 6),
                "Генерала дорохова" to setOf(1, 2, 3, 4, 6, 8),
            )
        ),
        "ЦАО" to mapOf(
            "Тверской" to mapOf(
                "Тверская" to setOf(2, 4, 5, 6, 7),
                "Лубянка" to setOf(3, 2, 1, 4, 5, 6),
                "Красная площадь" to setOf(1, 2, 3, 4, 6, 8),
                "Пушкинская" to setOf(1, 2, 3, 4, 6, 8),
                "Красная площадь" to setOf(1, 2, 3, 4, 6, 8),
            ),
        ),
        "ВАО" to mapOf(
            "Митино" to mapOf(
                "Шоссе" to setOf(3, 4, 5, 6, 7),
                "Ворота" to setOf(2),
            )
        )
    ),
    "Питер" to mapOf(
        "КАД" to mapOf(
            "Центральный" to mapOf(
                "Невский" to setOf(1, 2, 3, 4, 6, 7, 8),
            ),
            "Красноармейский" to mapOf(
                "Рубенштейн" to setOf(1, 3, 5, 6, 7),
                "Жуковского" to setOf(2, 4, 8),
                "Некрасовка" to setOf(4)
            ),
        ),
    )
)

fun main() {

    // все пропущенные дома, в формате 'город, округ, район, улица, номера дома'

    for ((city, circles) in data) {
        for ((circle, areas) in circles) {
            for ((area, streets) in areas) {
                for ((street, nums) in streets) {
                    for (i in 1..nums.max()) {
                        if (!nums.contains(i) && streets.keys.size > areas.keys.size) {
                            println("$city $circle $area $street $i")
                        }
                    }
                }
            }
        }
    }
    //только в тех районах где улиц больше чем районов в округе
//кольчество пропущеных домов больше чем существующих

}