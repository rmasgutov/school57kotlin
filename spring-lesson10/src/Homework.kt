import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

class DocumentationGenerator {
    fun generateDoc(obj: Any): String {
        val clazz = obj::class
        val classDoc = clazz.findAnnotation<DocClass>() ?: return "Нет документации для класса."
        val result = StringBuilder()
        result.append("=== Документация: ${clazz.simpleName} ===\n")
        result.append("Описание: ${classDoc.description}\n")
        result.append("Автор: ${classDoc.author}\n")
        result.append("Версия: ${classDoc.version}")
        val properties = clazz.declaredMemberProperties
            .filter { !it.hasAnnotation<InternalApi>() }
            .filterNot { it.name.startsWith("component") }
        if (properties.isNotEmpty()) {
            result.append("\n\n--- Свойства ---")
            properties.forEachIndexed { index, property ->
                result.append("\n- ${property.name}")
                val propertyDoc = property.findAnnotation<DocProperty>()
                if (propertyDoc != null) {
                    result.append("\n  Описание: ${propertyDoc.description}")
                    if (propertyDoc.example.isNotEmpty()) {
                        result.append("\n  Пример: ${propertyDoc.example}")
                    }
                }
                if (index < properties.size - 1) {
                    result.append("\n")
                }
            }
        }
        val methods = clazz.declaredMemberFunctions
            .filter { !it.hasAnnotation<InternalApi>() }
            .filterNot { it.name in setOf("toString", "equals", "hashCode", "copy") }
            .filterNot { it.name.startsWith("component") }

        if (methods.isNotEmpty()) {
            if ((properties.isNotEmpty()) or (result.isNotEmpty())) {
                result.append("\n")
            }
            result.append("\n--- Методы ---")
            methods.forEachIndexed { index, method ->
                val parameters = method.parameters
                    .filter { (it.name != null) and (it.name != "this") }
                    .joinToString { param ->
                        val paramName = param.name ?: ""
                        val paramType = param.type.toString().split(".").last()
                        "$paramName: $paramType"
                    }

                result.append("\n- ${method.name}($parameters)")
                val methodDoc = method.findAnnotation<DocMethod>()
                if (methodDoc != null) {
                    result.append("\n  Описание: ${methodDoc.description}")
                    val methodParams = method.parameters.filter { (it.name != null) and (it.name != "this") }
                    if (methodParams.isNotEmpty()) {
                        result.append("\n  Параметры:")
                        methodParams.forEach { param ->
                            val paramDoc = param.findAnnotation<DocParam>()
                            val paramName = param.name ?: ""
                            val paramType = param.type.toString().split(".").last()
                            result.append("\n    - $paramName: $paramType")
                            if (paramDoc != null) {
                                result.append("\n      Описание: ${paramDoc.description}")
                            }
                        }
                    }
                    if (methodDoc.returns.isNotEmpty()) {
                        result.append("\n  Возвращает: ${methodDoc.returns}")
                    }
                }
                if (index < methods.size - 1) {
                    result.append("\n")
                }
            }
        }
        return result.toString()
    }
}