package ru.tbank.education.school.lesson10.homework

import kotlin.reflect.KParameter
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.jvmErasure

object DocumentationGenerator {
    fun generateDoc(targetObject: Any): String {
        val targetClass = targetObject::class
        val classAnnotation = targetClass.findAnnotation<DocClass>() ?: return "Нет документации для класса."

        if (targetClass.findAnnotation<InternalApi>() != null) {
            return "Документация скрыта (InternalApi)."
        }
        val documentationBuilder = StringBuilder()
        val className = targetClass.simpleName ?: "Unknown"
        documentationBuilder.append("=== Документация: $className ===\n")
        documentationBuilder.append("Описание: ${classAnnotation.description}\n")
        documentationBuilder.append("Автор: ${classAnnotation.author}\n")
        documentationBuilder.append("Версия: ${classAnnotation.version}\n\n")
        val internalProperties = targetClass.declaredMemberProperties
            .filter { it.findAnnotation<InternalApi>() != null }.map { it.name }.toSet()
        val visibleProperties = targetClass.declaredMemberProperties.filter { it.findAnnotation<InternalApi>() == null }
        if (visibleProperties.isNotEmpty()) {
            documentationBuilder.append("--- Свойства ---\n")
            for (property in visibleProperties) {
                documentationBuilder.append("- ${property.name}\n")
                val propertyAnnotation = property.findAnnotation<DocProperty>()
                if (propertyAnnotation != null) {
                    documentationBuilder.append("  Описание: ${propertyAnnotation.description}\n")
                    if (propertyAnnotation.example.isNotEmpty()) {
                        documentationBuilder.append("  Пример: ${propertyAnnotation.example}\n")
                    }
                }
            }
            documentationBuilder.append("\n")
        }
        val excludedMethodNames = setOf("toString", "equals", "hashCode", "copy")
        val visibleMethods = targetClass.declaredMemberFunctions
            .filter { method ->
                if (method.findAnnotation<InternalApi>() != null) {
                    return@filter false
                }
                val methodName = method.name
                if (methodName in excludedMethodNames) {
                    return@filter false
                }
                if (methodName.startsWith("component")){
                    return@filter false
                }
                true
            }
        if (visibleMethods.isNotEmpty()) {
            documentationBuilder.append("--- Методы ---\n")
            for (method in visibleMethods) {
                val allParameters = method.parameters.filter { it.kind == KParameter.Kind.VALUE }
                val visibleParameters = allParameters.filter { parameter ->
                    val parameterName = parameter.name
                    parameterName == null || parameterName !in internalProperties
                }
                val parametersString = visibleParameters.joinToString(", ") { parameter ->
                    val typeName = parameter.type.jvmErasure.simpleName ?: parameter.type.toString()
                    "${parameter.name}: $typeName"
                }
                documentationBuilder.append("- ${method.name}($parametersString)\n")
                val methodAnnotation = method.findAnnotation<DocMethod>()
                if (methodAnnotation != null) {
                    documentationBuilder.append("  Описание: ${methodAnnotation.description}\n")
                }

                if (visibleParameters.isNotEmpty()) {
                    documentationBuilder.append("  Параметры:\n")
                    for (parameter in visibleParameters) {
                        val parameterAnnotation = parameter.findAnnotation<DocParam>()
                        val description = parameterAnnotation?.description ?: "Нет описания"
                        documentationBuilder.append("    - ${parameter.name}: $description\n")
                    }
                }

                val returnDescription = methodAnnotation?.returns ?: "Нет описания"
                documentationBuilder.append("  Возвращает: $returnDescription\n")
            }
        }
        return documentationBuilder.toString().trimEnd()
    }
}
