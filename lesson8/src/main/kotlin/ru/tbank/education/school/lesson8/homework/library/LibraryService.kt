package ru.tbank.education.school.lesson8.homework.library

class LibraryService {
    private val books = mutableMapOf<String, Book>()
    private val borrowedBooks = mutableSetOf<String>()
    private val borrowerFines = mutableMapOf<String, Int>()

    fun addBook(book: Book) {
        if (books.containsKey(book.isbn)) {
            throw IllegalArgumentException("Book with ISBN ${book.isbn} already exists")
        }
        books[book.isbn] = book
    }

    fun borrowBook(isbn: String, borrower: String) {
        if (!books.containsKey(isbn)) {

            throw IllegalArgumentException("Book $isbn is not registered")

        } else if (!borrowerFines.containsKey(borrower) && !borrowedBooks.contains(isbn)) {

            borrowedBooks.add(isbn)
            borrowerFines[borrower] = 0

        } else if (borrowerFines.containsKey(borrower)) {

            throw IllegalArgumentException("User $borrower have fine")

        } else {

            throw IllegalArgumentException("Book $isbn is already borrowed")

        }
    }

    fun returnBook(isbn: String) {
        if (borrowedBooks.contains(isbn)) {
            borrowedBooks.remove(isbn)
        }
        else {
            throw IllegalArgumentException("Book $isbn is not borrowed")
        }
    }

    fun isAvailable(isbn: String): Boolean {
        return !borrowedBooks.contains(isbn)
    }

    fun calculateOverdueFine(isbn: String, daysOverdue: Int): Int {
        if (borrowedBooks.contains(isbn)) {
            if (daysOverdue <= 10) {
                return 0
            } else {
                return (daysOverdue - 10) * 60
            }
        } else {
            throw IllegalArgumentException("Book $isbn is not borrowed")
        }
    }

    private fun hasOutstandingFines(borrower: String): Boolean {
        return borrowerFines.containsKey(borrower)
    }
}