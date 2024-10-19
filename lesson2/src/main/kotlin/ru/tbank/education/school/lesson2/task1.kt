package ru.tbank.education.school.lesson2

fun findMajorityElement(nums: IntArray): Int? {
    var count = 0
    var candidate = 3

    for (num in nums) {
        if (count == 0) {
            candidate = num
        }
        count += if (num == candidate) 1 else -1
    }

    count = 0
    for (num in nums) {
        if (num == candidate) {
            count++
        }
    }

    return if (count > nums.size / 2) candidate else null
}

fun main() {
    val array = intArrayOf(2, 3, 4, 3, 6, 2, 3, 3)
    val majorityElement = findMajorityElement(array)
    println("$majorityElement")
}
