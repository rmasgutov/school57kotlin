package ru.tbank.education.school.lesson3.RPGgame
import java.io.File
import java.util.Locale
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.system.exitProcess

// Функция битвы с использованием интерфейсов
fun engageBattle(plr: player, enm: enemy) {
    println("\n=== НАЧАЛО БИТВЫ ===")
    println("Вы сражаетесь с ${enm.name}!")

    var playerTurn = true
    while (enm.hp > 0 && plr.hp > 0) {
        println("\n---")
        println("❤️ Ваше HP: ${plr.hp.toInt()}")
        println("💀 HP ${enm.name}: ${enm.hp.toInt()}")
        println("---")

        if (playerTurn) {
            var actionCompleted = false
            while (!actionCompleted) {
                println("Ваш ход:")
                println("1 - Атаковать")
                println("2 - Использовать предмет")
                println("3 - Попытаться убежать")

                when (readln().toIntOrNull() ?: 1) {
                    1 -> {
                        plr.attack(enm)
                        actionCompleted = true
                    }
                    2 -> {
                        if (plr.inv.isEmpty()) {
                            println("Инвентарь пуст! Выберите другое действие.")
                            continue
                        }

                        println("Выберите предмет для использования:")
                        val itemsList = plr.inv.entries.toList()
                        itemsList.forEachIndexed { index, (item, quantity) ->
                            println("${index + 1} - ${item.itemName} (x$quantity) - ${item.itemDescription}")
                        }
                        println("${itemsList.size + 1} - Отмена")

                        val choice = readln().toIntOrNull()
                        when {
                            choice == null -> println("Неверный ввод!")
                            choice in 1..itemsList.size -> {
                                val selectedItem = itemsList[choice - 1].key
                                val used = plr.useItem(selectedItem)
                                if (used) {
                                    actionCompleted = true
                                } else {
                                    println("Не удалось использовать предмет. Выберите другое действие.")
                                }
                            }
                            choice == itemsList.size + 1 -> {
                                println("Отмена использования предмета.")
                                continue
                            }
                            else -> println("Неверный выбор!")
                        }
                    }
                    3 -> {
                        println("Вы сбежали из битвы!")
                        return
                    }
                    else -> {
                        println("Неверный выбор!")
                        continue
                    }
                }
            }
        } else {
            println("Ход ${enm.name}:")
            enm.attack(plr)
        }

        playerTurn = !playerTurn
    }

    when {
        plr.hp <= 0 -> {
            println("Вы проиграли битву ${enm.name}... Удачи в следующий раз!")
            File("data.txt").delete()
            File("inventory.json").delete()
            exitProcess(0)
        }
        enm.hp <= 0 -> {
            println("🎉 Вы победили ${enm.name}!")
            plr.completedLoc = true
            // Получаем лут за победу
            val loot = plr.currentLocation.getLoot()
            loot.forEach { item ->
                plr.getItem(item)
            }
        }
    }
}

fun saveGame(player: player) {
    val data = "${player.name},${player.stats.hp},${player.stats.mana},${player.stats.stamina},${player.stats.lvl},${player.stats.xp},${player.stats.clasS},${player.stats.race},${player.stats.power},${player.currentLocation.name},${player.hp},${player.completedLoc}"
    File("data.txt").writeText(data)
    val fl1 = File("inventory.json")
    val json = Json {
        prettyPrint = true // Для удобного чтения файла
        ignoreUnknownKeys = true // Если в будущем структура JSON может меняться
        allowStructuredMapKeys = true
    }
    val jsonString = json.encodeToString(player.inv)
    fl1.writeText(jsonString)
    println("Игра сохранена!")
}

fun loadGame(): player? {
    if (!File("data.txt").exists()) return null
    val fl = File("inventory.json")
    var decoded = mutableMapOf<item, Int>()
    val json = Json {
        prettyPrint = true // Для удобного чтения файла
        ignoreUnknownKeys = true // Если в будущем структура JSON может меняться
        allowStructuredMapKeys = true
    }
    if (fl.exists()){
        decoded = mutableMapOf<item, Int>(json.decodeFromString(fl.readText()))
    }
    val data = File("data.txt").readText()
    val parts = data.split(",")

    val stats = statblock(
        hp = parts[1].toFloat(),
        mana = parts[2].toFloat(),
        stamina = parts[3].toFloat(),
        lvl = parts[4].toInt(),
        xp = parts[5].toInt(),
        clasS = parts[6],
        race = parts[7],
        power = parts[8].toFloat()
    )
    println(parts[10].toFloat())
    val location = byName(parts[9])
    val player = player(parts[0], stats, decoded, location, parts[11].toBoolean(), parts[10].toFloat())

    // Добавляем базовые предметы

    return player
}

fun main() {
    println("Рад приветствовать вас в моём творение - PseudoRPG! Эта программа является скорее имитацией игры, а не готового продукта. Тем не менее, надеюсь вам понравится!)")
    println("\n\n\n")
    println("=== PSEUDO RPG ===")
    val player = loadGame()
    if (player != null) {
        println("Загружена сохраненная игра!")
        println("Добро пожаловать обратно, ${player.name}!")
        gameLoop(player)
    } else {
        println("Создание нового персонажа...")
        println("Введите имя персонажа:")
        var name = readln()
        while (name.contains(',')) {
            println("Имя не должно содержать знаков препинания! Повторите попытку:\n")
            name = readln()
        }
        println("Выберите класс:")
        println("1 - Воин (+5 к силе)")
        println("2 - Маг (+50 к мане)")
        val classChoice = readln().toIntOrNull() ?: 1

        val stats = when (classChoice) {
            2 -> statblock(clasS = "Маг", mana = 150f)
            else -> statblock(clasS = "Воин", power = 15f)
        }

        val player = player(name, stats, mutableMapOf(), hp = stats.hp)

        // Стартовые предметы
        player.getItem(item(1, "Простое зелье здоровья", "Обычное зелье лечения", mapOf("hp" to 5)), 2)

        if (classChoice == 1) {
            player.getItem(item(4, "Стальной меч", "Стальной меч. Выкован одним из селян.", mapOf("power" to 5, "hp" to 5)))
            player.equipItem(item(4, "Стальной меч", "Стальной меч. Выкован одним из селян.", mapOf("power" to 5, "hp" to 5)))
        } else {
            player.getItem(item(3, "Магический посох", "Магический посох. Никто не знает, откуда он в деревне", mapOf("power" to 10, "hp" to 15)))
            player.equipItem(item(3, "Магический посох", "Магический посох. Никто не знает, откуда он в деревне", mapOf("power" to 10, "hp" to 15)))
        }

        println("\n=== ИГРА НАЧИНАЕТСЯ ===")
        println("\nТы - обыкновенный ${player.stats.clasS}, живущий в ничем не примечательной деревушке. В последнее время, в деревне были замечены множественные случаи убийств, в основном, на телах погибших были рваные раны, нанесённые, по всей видимости, монстрами. Поэтому старейшина селения дал тебе задание:\n'${player.name}, я доверяю тебе очень важную миссию - разобраться с этой чертовщиной. К слову, у нас в деревни появился хулиган, устрой ему взбучку, по возможности.'")

        println("\nВы находитесь в: ${player.currentLocation.name}")
        println(player.currentLocation.description)

        gameLoop(player)
    }
}

fun gameLoop(player: player) {
    while (true) {
        println("\n=== ${player.currentLocation.name.uppercase(Locale.getDefault())} ===")
        println("HP: ${player.hp.toInt()}/${player.stats.hp.toInt()} | Уровень: ${player.stats.lvl}")
        println("1 - Исследовать локацию")
        println("2 - Отдохнуть")
        println("3 - Инвентарь")
        println("4 - Сохранить игру")
        println("5 - Выйти из игры")

        when (readln().toIntOrNull() ?: 1) {
            1 -> {
                if (!player.completedLoc) {
                    val enemy = player.currentLocation.encounterEnemy()
                    engageBattle(player, enemy)
                    if (player.completedLoc) {
                        player.stats.levelUp()
                    }
                } else {
                    // Переход на следующую локацию
                    player.currentLocation = when (player.currentLocation.name) {
                        "Деревня" -> DarkWoods()
                        "Тёмный лес" -> Cave()
                        "Пещера" -> {
                            println("\n🎉 ПОБЕДА! Вы очистили все локации и победили главного босса!")
                            println("Спасибо за игру!")
                            File("data.txt").delete()
                            exitProcess(0)
                        }
                        else -> Village()
                    }
                    player.completedLoc = false
                    println("\nВы перешли в: ${player.currentLocation.name}")
                    println(player.currentLocation.description)
                }
            }
            2 -> {
                player.rest()
                println("Вы отдохнули и восстановили здоровье")
            }
            3 -> {
                if (player.inv.isEmpty()) {
                    println("Инвентарь пуст! Выберите другое действие.")
                    continue
                }
                var actionCompleted = false
                println("Выберите предмет для использования:")
                val itemsList = player.inv.entries.toList()
                itemsList.forEachIndexed { index, (item, quantity) ->
                    println("${index + 1} - ${item.itemName} (x$quantity) - ${item.itemDescription}")
                }
                println("${itemsList.size + 1} - Отмена")

                val choice = readln().toIntOrNull()
                when {
                    choice == null -> println("Неверный ввод!")
                    choice in 1..itemsList.size -> {
                        val selectedItem = itemsList[choice - 1].key
                        val used = player.useItem(selectedItem)
                        if (used) {
                            actionCompleted = true
                        } else {
                            println("Не удалось использовать предмет. Выберите другое действие.")
                        }
                    }
                    choice == itemsList.size + 1 -> {
                        println("Отмена использования предмета.")
                        continue
                    }
                    else -> println("Неверный выбор!")
                }
//                player.displayInv()
//                if (player.inv.isNotEmpty()) {
//                    println("Использовать предмет? (y/n)")
//                    if (readln().equals("y", true)) {
//                        val item = player.inv.keys.first()
//                        player.useItem(item)
//                    }
//                }
            }
            4 -> saveGame(player)
            5 -> {
                saveGame(player)
                println("Игра сохранена. До свидания!")
                exitProcess(0)
            }
        }
    }
}