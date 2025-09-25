package ru.tbank.education.school.lesson3.RPGgame
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import kotlin.math.roundToInt
import kotlin.system.exitProcess

fun loadData(): Pair<String, MutableMap<item, Int>> {
    val file = File("data.txt")
    val fl = File("inventory.json")
    val json = Json {
        prettyPrint = true // Для удобного чтения файла
        ignoreUnknownKeys = true // Если в будущем структура JSON может меняться
        allowStructuredMapKeys = true
    }
    var A = ""
    var C = mutableMapOf(item(-1,"none","none",mapOf("hp" to 0)) to 0)
    if (file.exists()){
        A=file.readText()
    }
    else {
        file.writeText("")
    }
    if (fl.exists()){
        var B = fl.readText()
        val decoded : MutableMap<item, Int> = json.decodeFromString(B)
        C = decoded
    }
    else {
        fl.writeText("")
        var C = mutableMapOf(item(-1,"none","none",mapOf("hp" to 0)) to 0)
    }
    return Pair(A,C)
}

fun saveData(pI: player, inv: MutableMap<item, Int>) {
    val file = File("data.txt")
    val fl1 = File("inventory.json")
    var data = "${pI.name},${pI.stats.hp},${pI.stats.mana},${pI.stats.stamina},${pI.stats.lvl},${pI.stats.xp},${pI.stats.clasS},${pI.stats.race},${pI.stats.power},${pI.currentLocation.name},${pI.hp}"
    val json = Json {
        prettyPrint = true // Для удобного чтения файла
        ignoreUnknownKeys = true // Если в будущем структура JSON может меняться
        allowStructuredMapKeys = true
    }
    val jsonString = json.encodeToString(inv)
    fl1.writeText(jsonString)

    file.writeText(data)

}


fun engageBattle(plr: player, enm: enemy) {
    var current = 0
    while ((enm.hp > 0) and (plr.hp > 0)) {
        println("----------------------")
        println("[=] Ваше здоровье: ${plr.hp}\n[=] Здоровье ${enm.name}: ${enm.hp}\n----------------------")
        if (current==0){
            println("Нажмите Enter для атаки!")
            readln()
            val deDmg = plr.stats.power*(1+(1..50).random().toFloat()/100)
            println("Ты наносишь $deDmg урона!")
            enm.getHit(deDmg)
            current=1
        }
        else{
            Thread.sleep(800)
            println("${enm.name} наносит удар!")
            val ooop = enm.dmgD
            val ooo = enm.dmg + ((-ooop.roundToInt()..ooop.roundToInt()).random())
            println("Ты получаешь $ooo урона!")
            plr.getHit(ooo)
            current=0
        }
    }
    if (plr.hp <= 0) {
        println("Вы умерли. Вас одолел ${enm.name}.")
        val fl1 = File("data.txt")
        fl1.delete()
        val fl2 = File("inventory.json")
        fl2.delete()
        exitProcess(0)
    }
    if (enm.hp <= 0) {
        println("Битва окончена. ${enm.name} был повержен вами... Время продолжать свой путь, хотя, может быть, стоит отдохнуть.")
        plr.completedLoc = true

    }
}


fun main(){
    var innit = false
    var savedData = loadData()
    var playerStats= statblock()
    if (savedData.first=="") {
        println("Рад приветствовать вас в моём творение - PseudoRPG! Эта программа является скорее имитацией игры, а не готового продукта. Тем не менее, надеюсь вам понравится!)")
        println("\n\nПривет! Я ваш персональный помощник Орион, помогу освоиться в управлении. Для начала вам нужно создать персонажа. Придумайте ему имя: \n")
        val Cname = readln()
        println("\nЗамечательное имя! Теперь нужно выбрать расу из списка доступных (в ответ напишите цифру):\n1 - Человек\n2 - Эльф\n3 - Дварф\n4 - Орк\n5 - Полурослик\n")
        var jj = readln().toIntOrNull()
        var race = ""
        val aR = listOf(1, 2, 3, 4, 5)
        while (jj !in aR) {
            println("Ошибка. Введите цифру от 1 до 5:")
            jj = readln().toIntOrNull()
        }
        if (jj != null) {
            if ((0 < jj) and (jj < 6)) {
                when (jj) {
                    1 -> race = "Человек"
                    2 -> race = "Эльф"
                    3 -> race = "Дварф"
                    4 -> race = "Орк"
                    5 -> race = "Полурослик"
                }
            }
        }
        println("\nХороший выбор! Теперь нужно выбрать класс из списка доступных (в ответ напишите цифру):\n1 - Воин\n2 - Маг\n3 - Плут\n4 - Бард\n")
        var ju = readln().toIntOrNull()
        var claSS = ""
        val aC = listOf(1, 2, 3, 4)
        while (ju !in aC) {
            println("Ошибка. Введите цифру от 1 до 4:")
            ju = readln().toIntOrNull()
        }
        if (ju != null) {
            if ((0 < ju) and (ju < 5)) {
                when (ju) {
                    1 -> claSS = "Воин"
                    2 -> claSS = "Маг"
                    3 -> claSS = "Плут"
                    4 -> claSS = "Бард"
                }
            }
        }
        println("\nВсё готово! Запускаю для вас игру...\n\n\n\n")


        println("Ты - обыкновенный ${claSS}, живущий в ничем не примечательной деревушке. В последнее время, в деревне были замечены множественные случаи убийств, в основном, на телах погибших были рваные раны, нанесённые, по всей видимости, монстрами. Поэтому старейшина селения дал тебе задание:\n'${Cname}, я доверяю тебе очень важную миссию - разобраться с этой чертовщиной. К слову, у нас в деревни появился хулиган, устрой ему взбучку, по возможности.'")

        var playerStats = statblock(clasS = claSS, race = race)
        println(Cname)
        var playerInstance = player(Cname, playerStats, mutableMapOf(item(1,"Неизвестная таблетка","Вы когда-нибудь хотели стать лучшей версией себя?",mapOf("hp" to 100000, "power" to 50000)) to 1), Village(), false, playerStats.hp)
        if (claSS == "Маг") {
            playerInstance.getItem(playerInstance.inv, item(3, "Посох", "Магический посох, нужен для сражений.", mapOf("hp" to -5, "power" to 5)))
            val ouio = playerInstance.equipItem(playerInstance.inv, item(3, "Меч", "Стальной меч, нужен для сражений.", mapOf("hp" to 5, "power" to 1)))
            playerInstance.stats.hp += ouio["hp"]!!.toFloat()
            playerInstance.stats.power += ouio["power"]!!.toFloat()
        }
        else {
            playerInstance.getItem(playerInstance.inv, item(3, "Меч", "Стальной меч, нужен для сражений.", mapOf("hp" to 5, "power" to 1)))
            val ouio = playerInstance.equipItem(playerInstance.inv, item(3, "Меч", "Стальной меч, нужен для сражений.", mapOf("hp" to 5, "power" to 1)))
            playerInstance.stats.hp  += ouio["hp"]!!.toFloat()
            playerInstance.stats.power += ouio["power"]!!.toFloat()
        }

        saveData(playerInstance,playerInstance.inv)

        println("Вы сейчас в локации '${playerInstance.currentLocation.name}'.\n\n[${playerInstance.currentLocation.description}]")
        while(true) {

            println("Выберите действие:\n1 - Отдохнуть\n2 - Идти дальше\n3 - Где я?\n4 - Сохранить и выйти\n5 - Посмотреть инвентарь")
            var deistvie = readln().toIntOrNull()
            var dododo = 0
            val alls = listOf(1, 2, 3, 4, 5)
            while (deistvie !in alls) {
                println("Ошибка. Введите цифру от 1 до 5:")
                deistvie = readln().toIntOrNull()
            }
            if (deistvie != null) {
                if ((0 < deistvie) and (deistvie < 6)) {
                    when (deistvie) {
                        1 -> dododo = 0
                        2 -> dododo = 1
                        3 -> dododo = 2
                        4 -> dododo= 3
                        5 -> dododo = 4
                    }
                }
            }
            if (dododo==0) {
                playerInstance.rest()
                println("Теперь ваше здоровье составляет ${playerInstance.hp}")
            }
            if (dododo==2){
                println("Вы сейчас в локации '${playerInstance.currentLocation.name}'")
            }
            if (dododo==1) {
                if (!playerInstance.completedLoc) {
                    engageBattle(playerInstance, playerInstance.currentLocation.encounterEnemy())
                }
                else{
                    if (playerInstance.currentLocation.name == "Деревня") {
                        playerInstance.currentLocation= DarkWoods()
                    }
                    else if (playerInstance.currentLocation.name == DarkWoods().name) {
                        playerInstance.currentLocation = Cave()
                    }
                    playerInstance.completedLoc = false
                    println("Вы попали в локацию '${playerInstance.currentLocation.name}'.\n\n[${playerInstance.currentLocation.description}]")
                }
            }
            if (dododo==3) {
                saveData(playerInstance,playerInstance.inv)
                exitProcess(0)
            }
            if (dododo == 4) {
                playerInstance.displayInv(playerInstance.inv)
            }
            if ((playerInstance.completedLoc) and (playerInstance.currentLocation.name=="Пещера")) {
                println("\n\n\nПоздравляем! Вы прошли PseudoRPG!! Вы сражались браво!")
                val fl1 = File("data.txt")
                val fl2 = File("inventory.json")
                fl1.delete()
                fl2.delete()
                exitProcess(0)
            }
        }
    }
    else {
            println("\nВсё готово! Запускаю для вас игру...\n\n\n\n")

            var playerStats = statblock(savedData = savedData.first)
            val lll = savedData.first.split(',')[9]
            var playerInstance = player(savedData.first.split(",")[0], playerStats, savedData.second, hp = 0.0f)
        when (lll) {
            "Деревня" -> playerInstance = player(
                savedData.first.split(",")[0],
                playerStats,
                savedData.second,
                hp = 0.0f
            )
            "Тёмный лес" -> playerInstance = player(
                savedData.first.split(",")[0],
                playerStats,
                savedData.second,
                DarkWoods(),
                hp = 0.0f
            )
            "Пещера" -> playerInstance = player(
                savedData.first.split(",")[0],
                playerStats,
                savedData.second,
                Cave(),
                hp = 0.0f
            )
        }

            saveData(playerInstance, playerInstance.inv)

        println("Вы сейчас в локации '${playerInstance.currentLocation.name}'.\n\n[${playerInstance.currentLocation.description}]")
        while(true) {

            println("Выберите действие:\n1 - Отдохнуть\n2 - Идти дальше\n3 - Где я?\n4 - Сохранить и выйти\n5 - Посмотреть инвентарь") // !!! чтобы съесть таблетку, нужно ввести 6(пасхалка)
            var deistvie = readln().toIntOrNull()
            var dododo = 0
            val alls = listOf(1, 2, 3, 4, 5, 6)
            while (deistvie !in alls) {
                println("Ошибка. Введите цифру от 1 до 5:")
                deistvie = readln().toIntOrNull()
            }
            if (deistvie != null) {
                if ((0 < deistvie) and (deistvie < 7)) {
                    when (deistvie) {
                        1 -> dododo = 0
                        2 -> dododo = 1
                        3 -> dododo = 2
                        4 -> dododo= 3
                        5 -> dododo = 4
                        6 -> dododo = 5
                    }
                }
            }
            if (dododo==0) {
                playerInstance.rest()
                println("Вы отдохнули и теперь ваше здоровье составляет ${playerInstance.hp}")
            }
            if (dododo==2){
                println("Вы сейчас в локации '${playerInstance.currentLocation.name}'")
            }
            if (dododo==1) {
                if (!playerInstance.completedLoc) {
                    engageBattle(playerInstance, playerInstance.currentLocation.encounterEnemy())
                    playerInstance.stats.hp *= 1.6F
                    playerInstance.stats.power *= 1.5F
                }
                else{
                    if (playerInstance.currentLocation.name == "Деревня") {
                        playerInstance.currentLocation= DarkWoods()
                    }
                    else if (playerInstance.currentLocation.name == DarkWoods().name) {
                        playerInstance.currentLocation = Cave()
                    }
                    playerInstance.completedLoc = false
                    println("Вы попали в локацию '${playerInstance.currentLocation.name}'.\n\n[${playerInstance.currentLocation.description}]")
                }
            }
            if (dododo==3) {
                saveData(playerInstance,playerInstance.inv)
                exitProcess(0)
            }
            if (dododo == 4) {
                playerInstance.displayInv(playerInstance.inv)
            }

            if ((dododo == 5) and !innit) {
                val oui = playerInstance.useItem(playerInstance.inv, item(1,"Неизвестная таблетка","Вы когда-нибудь хотели стать лучшей версией себя?",mapOf("hp" to 100000, "power" to 50000)))
                println("[=Вы когда-нибудь хотели стать лучшей версией себя?=]")
                playerInstance.stats.hp= oui["hp"]!!.toFloat()
                playerInstance.stats.hp= oui["power"]!!.toFloat()
                innit = true

            }

            if ((playerInstance.completedLoc) and (playerInstance.currentLocation.name=="Пещера")) {
                println("\n\n\nПоздравляем! Вы прошли PseudoRPG!! Вы сражались браво!")
                val fl1 = File("data.txt")
                val fl2 = File("inventory.json")
                fl1.delete()
                fl2.delete()
                exitProcess(0)
            }

        }
        }

}