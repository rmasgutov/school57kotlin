package homework


/**
 *
 * В данном классе `BankAccount` реализован метод `transfer()` для перевода денег
 * с одного счета на другой. Однако в текущей реализации присутствует
 * **дедлок (deadlock)** - ситуация взаимной блокировки потоков.
 *
 * Проблема:
 * Дедлок возникает при одновременном выполнении двух переводов в противоположных направлениях:
 *
 * Задание:
 * 1. Проанализируйте код и воспроизведите дедлок с помощью тестов
 * 2. Исправьте метод `transfer()` так, чтобы дедлок был невозможен
 * 3. Убедитесь, что все тесты проходят
 */
class BankAccount(val id: String, var balance: Int) {

    fun transfer(to: BankAccount, amount: Int) {
        synchronized(this) {
            Thread.sleep(10)
            
            synchronized(to) {
                if (balance >= amount) {
                    balance -= amount
                    to.balance += amount
                }
            }
        }
    }
}