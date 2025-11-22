package ru.tbank.education.school.lesson8.homework.library

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName

class LibraryServiceTest {

    private lateinit var library: LibraryService

    @BeforeEach
    fun setUp() {
        library = LibraryService()
    }

    @Test
    @DisplayName("Книгу можно взять, если она доступна")
    fun bookCanBeBorrowed() {
        val book = Book("1984", "George Orwell", "978-0-452-28423-4")

        library.addBook(book)
        library.borrowBook("978-0-452-28423-4", "Ivan")

        assertFalse(library.isAvailable("978-0-452-28423-4"))
    }

    @Test
    @DisplayName("Нельзя взять одну и ту же книгу дважды")
    fun cannotBorrowSameBookTwice() {
        val book = Book("1984", "George Orwell", "978-0-452-28423-4")

        library.addBook(book)
        library.borrowBook("978-0-452-28423-4", "Ivan")

        assertThrows(IllegalArgumentException::class.java) {
            library.borrowBook("978-0-452-28423-4", "Petr")
        }
    }

    @Test
    @DisplayName("После возврата книга становится доступной")
    fun returningBookMakesItAvailable() {
        val book = Book("1984", "George Orwell", "978-0-452-28423-4")

        library.addBook(book)
        library.borrowBook("978-0-452-28423-4", "Ivan")
        library.returnBook("978-0-452-28423-4")

        assertTrue(library.isAvailable("978-0-452-28423-4"))
    }

    @Test
    @DisplayName("Нельзя вернуть книгу, которая не была выдана")
    fun cannotReturnNotBorrowedBook() {
        assertThrows(IllegalArgumentException::class.java) {
            library.returnBook("978-0-452-28423-4")
        }
    }

    @Test
    @DisplayName("Нельзя взять книгу, которой нет в каталоге")
    fun cannotBorrowNonExistentBook() {
        assertThrows(IllegalArgumentException::class.java) {
            library.borrowBook("978-0-452-28423-4", "Ivan")
        }
    }

    @Test
    @DisplayName("Штраф не начисляется в пределах льготного периода")
    fun noFineWithinGracePeriod() {
        val book = Book("Dune", "Frank Herbert", "978-0-441-17271-9")

        library.addBook(book)
        library.borrowBook("978-0-441-17271-9", "Alice")

        val fine = library.calculateOverdueFine("978-0-441-17271-9", daysOverdue = 7)
        assertEquals(0, fine)
    }

    @Test
    @DisplayName("Штраф рассчитывается корректно при просрочке")
    fun overdueFineCalculatedCorrectly() {
        val book = Book("Dune", "Frank Herbert", "978-0-441-17271-9")

        library.addBook(book)
        library.borrowBook("978-0-441-17271-9", "Alice")

        val fine = library.calculateOverdueFine("978-0-441-17271-9", daysOverdue = 15)
        assertEquals(300, fine)
    }

    @Test
    @DisplayName("Читатель с непогашенным штрафом не может брать книги")
    fun cannotBorrowWithOutstandingFine() {
        val book1 = Book("1984", "George Orwell", "978-0-452-28423-4")
        val book2 = Book("Dune", "Frank Herbert", "978-0-441-17271-9")

        library.addBook(book1)
        library.addBook(book2)
        library.borrowBook("978-0-452-28423-4", "Ivan")

        assertThrows(IllegalArgumentException::class.java) {
            library.borrowBook("978-0-441-17271-9", "Ivan")
        }
    }
}