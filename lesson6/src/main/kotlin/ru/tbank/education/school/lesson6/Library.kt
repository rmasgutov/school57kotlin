package ru.tbank.education.school.lesson6

// Класс книги
data class Book(val title: String, val author: String, val year: Int, val genre: String)

// Класс пользователя
data class User(val name: String, val id: Int)

// Класс библиотеки
class Library {
    private val catalog = mutableListOf<Book>()
    private val users = mutableSetOf<User>()
    private val borrowedBooks = mutableMapOf<User, MutableList<Book>>()

    fun addBook(book: Book) {
        catalog.add(book)
    }

    fun registerUser(user: User) {
        users.add(user)
        if (!borrowedBooks.containsKey(user)) {
            borrowedBooks[user] = mutableListOf()
        }
    }

    fun lendBook(user: User, book: Book) {
        if (!users.contains(user)) return
        var index = -1
        for (i in 0 until catalog.size) {
            if (catalog[i] == book) {
                index = i
                break
            }
        }
        if (index == -1) return
        val b = catalog.removeAt(index)
        val list = borrowedBooks[user] ?: mutableListOf<Book>().also { borrowedBooks[user] = it }
        list.add(b)
    }

    fun returnBook(user: User, book: Book) {
        val list = borrowedBooks[user] ?: return
        var index = -1
        for (i in 0 until list.size) {
            if (list[i] == book) {
                index = i
                break
            }
        }
        if (index == -1) return
        val b = list.removeAt(index)
        catalog.add(b)
    }

    fun listBooksSortedByYear() {
        val copy = catalog.toMutableList()
        for (i in 0 until copy.size - 1) {
            for (j in 0 until copy.size - i - 1) {
                if (copy[j].year > copy[j + 1].year) {
                    val tmp = copy[j]
                    copy[j] = copy[j + 1]
                    copy[j + 1] = tmp
                }
            }
        }
        for (b in copy) {
            println("${b.year} — ${b.author}: ${b.title} [${b.genre}]")
        }
    }

    fun findBooksByAuthor(author: String) {
        val result = mutableListOf<Book>()
        for (b in catalog) {
            if (b.author == author) result.add(b)
        }
        for ((_, list) in borrowedBooks) {
            for (b in list) {
                if (b.author == author) result.add(b)
            }
        }
        for (b in result) {
            println("${b.author}: ${b.title} (${b.year}) [${b.genre}]")
        }
    }

    fun groupBooksByGenre() {
        val groups = mutableMapOf<String, MutableList<Book>>()
        for (b in catalog) {
            val list = groups[b.genre] ?: mutableListOf<Book>().also { groups[b.genre] = it }
            list.add(b)
        }
        for ((_, list) in borrowedBooks) {
            for (b in list) {
                val l = groups[b.genre] ?: mutableListOf<Book>().also { groups[b.genre] = it }
                l.add(b)
            }
        }
        for ((g, list) in groups) {
            println("$g:")
            for (b in list) {
                println("  ${b.author}: ${b.title} (${b.year})")
            }
        }
    }

    fun userBooks(user: User) {
        val list = borrowedBooks[user]
        if (list == null || list.isEmpty()) {
            println("У пользователя нет книг")
            return
        }
        for (b in list) {
            println("${b.author}: ${b.title} (${b.year}) [${b.genre}]")
        }
    }

    fun mostActiveUser() {
        var maxUser: User? = null
        var maxCount = -1
        for ((u, list) in borrowedBooks) {
            val count = list.size
            if (count > maxCount) {
                maxCount = count
                maxUser = u
            }
        }
        if (maxUser == null) {
            println("Нет данных")
        } else {
            println("Самый активный: ${maxUser.name} (книг: $maxCount)")
        }
    }

    fun genreStatistics() {
        val stats = mutableMapOf<String, Int>()
        for (b in catalog) {
            val c = stats[b.genre] ?: 0
            stats[b.genre] = c + 1
        }
        for ((_, list) in borrowedBooks) {
            for (b in list) {
                val c = stats[b.genre] ?: 0
                stats[b.genre] = c + 1
            }
        }
        for ((g, c) in stats) {
            println("$g: $c")
        }
    }
}

fun main() {
    val library = Library()

    // Пример работы с системой:
    // 1. Добавить книги
    // 2. Зарегистрировать пользователей
    // 3. Выдать и вернуть книги
    // 4. Вызвать методы отчетов
}