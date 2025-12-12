package ru.tbank.education.school.lesson10.dependency.injection.services

import ru.tbank.education.school.lesson10.dependency.injection.model.Message
import ru.tbank.education.school.lesson10.dependency.injection.model.Person

/**
 * Интерфейс сервиса для отправки сообщений.
 * Определяет контракт для бизнес-логики отправки сообщений пользователям.
 */
interface MessageService {
    /**
     * Отправляет сообщение указанному пользователю.
     *
     * @param person получатель сообщения
     * @param message содержимое сообщения
     */
    fun sendMessage(person: Person, message: Message)
}
