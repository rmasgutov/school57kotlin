package ru.tbank.education.school.lesson2

class first {


    fun main() {
        val lis = listOf(2, 3, 4, 3, 6, 2, 3 ,3)
        val leng = lis.size / 2
        for (n in lis){
            var c = 0
            for (j in lis){
                if (j == n) {
                    c += 1
                }

            }
            if (c == leng) {
                println(n)
                break
            }
        }
    }

}