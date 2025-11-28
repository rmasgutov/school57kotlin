package ru.tbank.education.school.lesson8.homework.library

class LibraryService {
    private val books = mutableMapOf<String, Book>()
    private val borrowedBooks = mutableSetOf<String>()
    private val borrowerFines = mutableMapOf<String, Int>()
    private val bookBorrower = mutableMapOf<String, String>()

    fun addBook(book: Book) {
        if (book.isbn.isBlank()) {
            throw IllegalArgumentException("ISBN can't be blank.")
        }
        if (books.containsKey(book.isbn)) {
            throw IllegalArgumentException("Book with ISBN ${book.isbn} already exists.")
        }
        books[book.isbn] = book
    }

    fun borrowBook(isbn: String, borrower: String) {
        if (isbn.isBlank()) {
            throw IllegalArgumentException("ISBN can't be blank.")
        }
        if (borrower.isBlank()) {
            throw IllegalArgumentException("Borrower name can't be blank.")
        }
        if (!books.containsKey(isbn)) {
            throw IllegalArgumentException("Book with ISBN $isbn not in catalog.")
        }

        if (hasOutstandingFines(borrower)) {
            throw IllegalArgumentException("Borrower $borrower has fines.")
        }

        if (bookBorrower.containsValue(borrower)) {
            throw IllegalArgumentException("Borrower $borrower must return previous book first.")
        }

        if (borrowedBooks.contains(isbn)) {
            throw IllegalArgumentException("Book with ISBN $isbn already borrowed.")
        }

        borrowedBooks.add(isbn)
        bookBorrower[isbn] = borrower
    }


    fun returnBook(isbn: String) {
        if (isbn.isBlank()) {
            throw IllegalArgumentException("ISBN can't be blank")
        }
        if (!borrowedBooks.contains(isbn)) {
            throw IllegalArgumentException("Book with ISBN $isbn wasn't borrowed.")
        }
        borrowedBooks.remove(isbn)
        bookBorrower.remove(isbn)
    }

    fun isAvailable(isbn: String): Boolean {
        if (isbn.isBlank()) {
            throw IllegalArgumentException("ISBN can't be blank.")
        }
        if (!books.containsKey(isbn)) {
            throw IllegalArgumentException("Book with ISBN $isbn not in catalog.")
        }
        return !borrowedBooks.contains(isbn)
    }

    fun calculateOverdueFine(isbn: String, daysOverdue: Int): Int {
        if (isbn.isBlank()) {
            throw IllegalArgumentException("ISBN can't be blank")
        }
        if (daysOverdue < 0) {
            throw IllegalArgumentException("Days overdue can't be negative")
        }
        if (!books.containsKey(isbn)) {
            throw IllegalArgumentException("Book with ISBN $isbn not in catalog")
        }

        if (daysOverdue <= 10) return 0

        val fine = (daysOverdue - 10) * 60

        val borrower = bookBorrower[isbn]
        if (borrower != null) {
            borrowerFines[borrower] =
                borrowerFines.getOrDefault(borrower, 0) + fine
        }

        return fine
    }

    private fun hasOutstandingFines(borrower: String): Boolean {
        return borrowerFines.getOrDefault(borrower, 0) > 0
    }
}
