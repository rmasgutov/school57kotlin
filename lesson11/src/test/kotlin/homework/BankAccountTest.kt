package homework

import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

class BankAccountTest {
    
    @Test
    fun `simultaneous transfers should not deadlock`() {
        val account1 = BankAccount("ACC1", 1000)
        val account2 = BankAccount("ACC2", 1000)
        
        val latch = CountDownLatch(2)
        
        // Перевод из ACC1 в ACC2
        val thread1 = Thread {
            account1.transfer(account2, 100)
            latch.countDown()
        }
        
        // Перевод из ACC2 в ACC1 (обратное направление)
        val thread2 = Thread {
            account2.transfer(account1, 100)
            latch.countDown()
        }
        
        thread1.start()
        thread2.start()
        
        val completed = latch.await(2, TimeUnit.SECONDS)
        
        assert(completed) { "Обнаружен deadlock при одновременных переводах" }

        assertEquals(2000, account1.balance + account2.balance)
    }
    
    @Test
    fun `single transfer should work correctly`() {
        val account1 = BankAccount("ACC1", 1000)
        val account2 = BankAccount("ACC2", 1000)
        
        account1.transfer(account2, 300)
        
        assertEquals(700, account1.balance)
        assertEquals(1300, account2.balance)
    }
}