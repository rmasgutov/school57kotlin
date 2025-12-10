package ru.tbank.education.school.lesson10.dependency.injection.services

import ru.tbank.education.school.lesson10.dependency.injection.annotations.Component
import ru.tbank.education.school.lesson10.dependency.injection.model.Message
import ru.tbank.education.school.lesson10.dependency.injection.model.Person

/**
 * Реализация сервиса отправки сообщений.
 *
 * Помечен аннотацией @Component, поэтому будет автоматически
 * зарегистрирован в DI-контейнере при сканировании пакетов.
 */
@Component
class MessageServiceImpl : MessageService {
    /**
     * Отправляет сообщение пользователю.
     * В реальном приложении здесь была бы бизнес-логика отправки
     * (например, через email, push-уведомления и т.д.)
     */
    override fun sendMessage(person: Person, message: Message) {
        // BUSINESS LOGIC
        println("Message was successfully sent")
    }
}
