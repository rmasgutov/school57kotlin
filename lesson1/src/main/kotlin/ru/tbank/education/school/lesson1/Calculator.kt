package ru.tbank.education.school.lesson1
fun calculate(operation: String): Double {
    var i1 = ""
    var i2 = ""
    var i3 = 0
    for (i in operation) {
        if (i != ' ' && i != '-' && i != '/' && i != '+' && i != '*'){
            if (i3 != 0) {
                i2 += i
            } else {
                i1 += i
            }
        } else {
            if (i == '*') {
                i3 = 1
            }
            if (i == '+') {
                i3 = 2
            }
            if (i == '-') {
                i3 = 3
            }
            if (i == '/') {
                i3 = 4
            }
        }
    }
    var c = 0.0
    val a1 = i1.toDouble()
    val a2 = i2.toDouble()
    if (i3 == 1){
        c = a1 * a2
    }
    if (i3 == 2){
        c = a1 + a2
    }
    if (i3 == 3){
        c = a1 - a2
    }
    if (i3 == 4){
        c = a1 / a2
    }
    return c
}
fun main(){
    println(calculate("5 - 2"))
}
