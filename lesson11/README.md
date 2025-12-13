# Семинар: Многопоточность и корутины в Kotlin

---

## Часть 1. Потоки (Thread)

### Задание 1. Создание потоков
Создайте 3 потока с именами "Thread-A", "Thread-B", "Thread-C". Каждый поток должен вывести своё имя 5 раз с задержкой 500мс.

```kotlin
object CreateThreads {
    fun run(): List<Thread> {
        TODO()
    }
}
```

### Задание 2. Race condition
Создайте переменную `counter = 0`. Запустите 10 потоков, каждый из которых увеличивает counter на 1000. Выведите финальное значение и объясните результат.

```kotlin
object RaceCondition {
    fun run(): Int {
        TODO()
    }
}
```

### Задание 3. Synchronized
Исправьте задание 2 с помощью `@Synchronized` или `synchronized {}` блока, чтобы результат всегда был 10000.

```kotlin
object SynchronizedCounter {
    fun run(): Int {
        TODO()
    }
}
```

### Задание 4. Deadlock
Создайте пример deadlock с двумя ресурсами и двумя потоками. Затем исправьте его.

```kotlin
object Deadlock {
    fun runDeadlock() {
        TODO()
    }

    fun runFixed(): Boolean {
        TODO()
    }
}
```

---

## Часть 2. Executor Framework

### Задание 5. ExecutorService
Используя `Executors.newFixedThreadPool(4)`, выполните 20 задач. Каждая задача выводит свой номер и имя потока, затем спит 200мс.

```kotlin
object ExecutorServiceExample {
    fun run(): List<String> {
        TODO()
    }
}
```

### Задание 6. Future
Используя ExecutorService и `Callable`, параллельно вычислите факториалы чисел от 1 до 10. Соберите результаты через `Future.get()`.

```kotlin
object FutureFactorial {
    fun run(): Map<Int, BigInteger> {
        TODO()
    }
}
```

---

## Часть 3. Корутины

### Задание 7. Первая корутина
Используя `runBlocking` и `launch`, запустите 3 корутины, каждая из которых выводит своё имя 5 раз с `delay(500)`.

```kotlin
object CoroutineLaunch {
    fun run(): List<String> = runBlocking {
        TODO()
    }
}
```

### Задание 8. async/await
Используя `async`, параллельно вычислите сумму чисел от 1 до 1_000_000, разбив на 4 части. Соберите результаты через `await()`.

```kotlin
object AsyncAwait {
    fun run(): Long = runBlocking {
        TODO()
    }
}
```

### Задание 9. Structured concurrency
Создайте корутину, которая запускает 5 дочерних корутин. Если одна из них падает с исключением, все остальные должны отмениться.

```kotlin
object StructuredConcurrency {
    fun run(failingCoroutineIndex: Int): Int = runBlocking {
        TODO()
    }
}
```

### Задание 10. withContext
Используя `withContext(Dispatchers.IO)`, прочитайте содержимое 3 файлов параллельно и объедините результаты.

```kotlin
object WithContextIO {
    fun run(filePaths: List<String>): Map<String, String> = runBlocking {
        TODO()
    }
}
```

---

## Часть 4. Практическое задание

### Задание 11. Многопоточный загрузчик изображений

Напишите программу, которая параллельно скачивает изображения из интернета.

**Требования:**
1. Использовать корутины с `Dispatchers.IO`
2. Скачать 10 изображений с https://picsum.photos/200/300
3. Сохранить в папку `downloads/`
4. Вывести прогресс: "Downloaded 1/10", "Downloaded 2/10", ...
5. В конце вывести статистику: общее время, количество успешных/неуспешных загрузок

```kotlin
object ImageDownloader {
    fun run(urls: List<String>, outputDir: String): DownloadStats = runBlocking {
        TODO()
    }
}
```

---
