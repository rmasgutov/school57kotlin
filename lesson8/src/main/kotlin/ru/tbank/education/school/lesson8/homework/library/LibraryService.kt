package ru.tbank.education.school.lesson8.homework.library
import java.time.LocalDate
class LibraryService {
    private val books = mutableMapOf<String, Book>()
    private val borrowedBooks = mutableMapOf<String, String>()
    private val borrowerFines = mutableMapOf<String, Int>()

    fun addBook(book: Book) {
        books[book.isbn] = book
    }

    fun borrowBook(isbn: String, borrower: String,daysOverdue: Int = 0) {
        if (isbn in borrowedBooks) {
            throw IllegalArgumentException("Книга с ISBN $isbn уже взята")
        }
        if ((books.containsKey(isbn))==false){
            throw IllegalArgumentException("Книга с ISBN $isbn не найдена")
        }
        if (hasOutstandingFines(borrower)) {
            throw IllegalArgumentException("Книга не может быть выдана. У заёмщика есть задолженность")
        }
        if (daysOverdue > 10) {
            val fineAmount = (daysOverdue - 10) * 60
            addFine(borrower, fineAmount)
        }
        borrowedBooks[isbn] = borrower
    }

    fun returnBook(isbn: String,daysOverdue: Int = 0,) {
        if (borrowedBooks.contains(isbn)) {
            borrowedBooks.remove(isbn)
//            if (daysOverdue > 10) {
//                val fineAmount = (daysOverdue - 10) * 60
//                addFine(borrower, fineAmount)
//                // Нужно как-то определить, кто брал книгу
//                // Для этого нужно хранить информацию о том, кто какую книгу взял
//            }
        }else {
           throw IllegalArgumentException("Книга с ISBN $isbn не найдена")
//        }
        }
    }

    fun isAvailable(isbn: String): Boolean {
        return !borrowedBooks.contains(isbn)
    }

    fun calculateOverdueFine(isbn: String, daysOverdue: Int): Int {
        if (!borrowedBooks.contains(isbn)) {
            return 0
        }
        else if (daysOverdue<=10){
            return 0
        } else {
            return (daysOverdue-10)*60
        }
    }

    // Приватная функция для проверки
    fun hasOutstandingFines(borrower: String): Boolean {
        return (borrowerFines[borrower] ?: 0) > 0
    }
    fun addFine(borrower: String, amount: Int) {
        borrowerFines[borrower] = (borrowerFines[borrower] ?: 0) + amount
    }
}
