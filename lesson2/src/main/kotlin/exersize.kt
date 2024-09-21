fun main() {
    var arr = listOf(1, 2, 2, 2, 2, 3, 4)
    val n = arr.size
    arr = arr.sorted()
    var x = -1
    var k = 0
    for (i in arr) {
        if (i == x) {
            k++
        } else {
            x = i
            k = 1
        }
        if (k > n / 2) {
            println(x)
            break
        }
    }
}