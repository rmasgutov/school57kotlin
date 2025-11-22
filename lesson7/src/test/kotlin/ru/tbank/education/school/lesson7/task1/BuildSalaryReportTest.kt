package ru.tbank.education.school.lesson7.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task1.Employee
import ru.tbank.education.school.lesson7.practise.task1.buildSalaryReport

class BuildSalaryReportTest {

    @Test
    fun `calculates total, average and departments count`() {
        val employees = listOf(
            Employee("Anna", 100_000.0, "IT"),
            Employee("Ivan", 80_000.0, "IT"),
            Employee("Olga", 70_000.0, "HR")
        )

        val report = buildSalaryReport(employees)

        assertEquals(250_000.0, report.totalSalary, 0.001)
        assertEquals(83_333.33, report.avgSalary, 0.1)
        assertEquals(2, report.departmentsCount)
    }

    @Test
    fun `handles single employee`() {
        val employees = listOf(Employee("Solo", 50_000.0, "Finance"))

        val report = buildSalaryReport(employees)

        assertEquals(50_000.0, report.totalSalary, 0.001)
        assertEquals(50_000.0, report.avgSalary, 0.001)
        assertEquals(1, report.departmentsCount)
    }

    @Test
    fun `handles empty list`() {
        val report = buildSalaryReport(emptyList())

        assertEquals(0.0, report.totalSalary, 0.001)
        assertEquals(0.0, report.avgSalary, 0.001)
        assertEquals(0, report.departmentsCount)
    }

    @Test
    fun `counts unique departments correctly`() {
        val employees = listOf(
            Employee("A", 10_000.0, "IT"),
            Employee("B", 20_000.0, "IT"),
            Employee("C", 30_000.0, "HR"),
            Employee("D", 40_000.0, "Finance")
        )

        val report = buildSalaryReport(employees)

        assertEquals(100_000.0, report.totalSalary, 0.001)
        assertEquals(25_000.0, report.avgSalary, 0.001)
        assertEquals(3, report.departmentsCount)
    }
}
