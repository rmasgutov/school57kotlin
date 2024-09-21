fun sorting(arr : Array <Int>) {
    arr.sort()
}

fun main() {
    var simpleArray = arrayOf(4, 5, 1, 2, 3)
    sorting(simpleArray)
    for (i in simpleArray) {
        print(i)
        print(' ')
    }
}