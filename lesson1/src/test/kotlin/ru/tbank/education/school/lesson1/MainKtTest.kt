package ru.tbank.education.school.lesson1

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import org.junit.jupiter.api.Test

class MainKtTest {
    @Test
    fun checkUsageSystemClass() {
        val jc: JavaClasses = ClassFileImporter().withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ru.tbank.education.school.lesson1")
        val r1: ArchRule = ArchRuleDefinition.classes().that().haveSimpleNameContaining("MainKt").should()
            .callMethod(java.io.PrintStream::class.java, "println", Object::class.java)
        r1.check(jc)
    }
}
