package ru.tbank.education.school.lesson10.homework

import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties

object DocumentationGenerator {
    fun generateDoc(obj: Any): String {
        val dataClassIgnoredMethods = setOf("toString", "hashCode", "equals", "copy")

        val kClass = obj::class

        if (kClass.findAnnotation<InternalApi>() != null) {
            return "Документация скрыта (InternalApi)."
        }

        val docClass = kClass.findAnnotation<DocClass>() ?: return "Нет документации для класса."

        val documentation = StringBuilder()
        documentation.appendLine("=== Документация: ${kClass.simpleName} ===")
        documentation.appendLine("Описание: ${docClass.description}")
        documentation.appendLine("Автор: ${docClass.author}")
        documentation.appendLine("Версия: ${docClass.version}")
        documentation.appendLine()

        val properties = kClass.memberProperties.filter { prop ->
            prop.findAnnotation<InternalApi>() == null &&
            prop.getter.findAnnotation<InternalApi>() == null
        }

        if (!properties.isEmpty()) {
            documentation.appendLine("--- Свойства ---")
            for (property in properties) {
                documentation.appendLine("- ${property.name}")
                val docProperty = property.findAnnotation<DocProperty>()
                if (docProperty != null) {
                    documentation.appendLine("  Описание: ${docProperty.description}")
                    if (!docProperty.example.isBlank()) {
                        documentation.appendLine("  Пример: ${docProperty.example}")
                    }
                }
                documentation.appendLine()
            }
        }

        val methods = kClass.memberFunctions.filter {
            it.findAnnotation<InternalApi>() == null &&
            !dataClassIgnoredMethods.contains(it.name) &&
            !it.name.startsWith("component")
        }

        if (!methods.isEmpty()) {
            documentation.appendLine("--- Методы ---")
            for (method in methods) {
                val params = method.parameters.filter {
                    it.kind == KParameter.Kind.VALUE
                }

                val signature = params.joinToString(", ") {
                    it.type.toString().removePrefix("kotlin.")
                }

                documentation.appendLine("- ${method.name}($signature)")

                val docMethod = method.findAnnotation<DocMethod>()
                if (docMethod != null) {
                    documentation.appendLine("  Описание: ${docMethod.description}")
                }

                if (!params.isEmpty()) {
                    documentation.appendLine("  Параметры:")
                    for (param in params) {
                        val docParam = param.findAnnotation<DocParam>()
                        if (docParam != null) {
                            documentation.appendLine("    -${param.name}: ${docParam.description}")
                        } else {
                            documentation.appendLine("    -${param.name}")
                        }
                    }
                }

                if (docMethod != null) {
                    documentation.appendLine("    Возвращает: ${docMethod.returns}")
                }
            }
        }

        return documentation.toString().trimEnd()
    }
}