package ru.tbank.education.school.lesson3.RPGgame

abstract class location {
    abstract val name: String
    abstract val description: String
    abstract fun encounterEnemy() : enemy
}

class DarkWoods : location() {
    override val name = "Тёмный лес"
    override val description = "Воздух в этом лесу ощущается в разы более тяжёлым, чем в твоей родной деревне. По всюду туман, а верхушки деревьев не пропускают солнечного света. Единственное, что помогает здесь ориентироваться - еле заметная тропинка, вытоптанная, вероятно, предыдущими путниками..."
    override fun encounterEnemy() : enemy {
        println("Из-за одного из деревьев этого ужасно тёмного леса выходит тролль. По вашему телу пробегает холод. Время битвы!")
        return enemy("Тролль", statblock(200F, 0F, 150F, 6,0,"None","Тролль",power=15F),10F,2F)
    }
}
class Village : location() {
    override val name = "Деревня"
    override val description = "Твой родимый дом - вокруг знакомые дома, люди. Приятное и дорогое твоему сердцу место."
    override fun encounterEnemy() : enemy {
        println("Зайдя за угол одного из домов, ты встречаешь местного задиру.\n'Ха, очередной простофиля. Сейчас я повеселюсь!'\nДраки не избежать...")
        return enemy("Хулиган", statblock(50F, 0F, 30F, 1, 0, "Воин","Человек", power=1F),4F,0.5F)
    }
}

class Cave : location() {
    override val name = "Пещера"
    override val description = "Зайдя в пещеру, ты видишь только проход вперёд, в темноту. Вокруг лежат кости, черепа, причём и животных, и тебе подобных... Жуткое место."
    override fun encounterEnemy() : enemy {
        println("Пройдя еще глубже в пещеру, ты видишь перед собой большой каменный трон, на котором восседает ужасающий упырь. Видимо именно он виновник всех проблем и монстров, бродящих вокруг. Убить его - предотвратить большие жертвы сельчан. 'Его нужно убить. Любой ценой.'")
        return enemy("Огромный упырь", statblock(350F, 0F, 250F, 10,0,"None", "Упырь", 20F),20F,5F)
    }
}