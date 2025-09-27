package ru.tbank.education.school.lesson3.seminar.sealedclasses

// Возможные состояния формы входа
sealed class LoginState {
    object Empty : LoginState()
    data class Filled(val username: String, val password: String) : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}



fun main() {
    val state: LoginState = LoginState.Filled("Alice", "12345")

    when (state) {
        is LoginState.Empty -> println("Форма пустая")
        is LoginState.Filled -> println("Пользователь: ${state.username}, пароль: ${state.password}")
        is LoginState.Loading -> println("Вход в систему...")
        is LoginState.Success -> println("Добро пожаловать!")
        is LoginState.Error -> println("Ошибка: ${state.message}")
    }
}