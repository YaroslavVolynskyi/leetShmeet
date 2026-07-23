import kotlin.math.ceil
import kotlin.math.max

fun main() {
    var array = intArrayOf(1, 66, 3, 1, 9,   17,   6, 4, 0, 47, 23, -3)
    qsort(array)
    println(array.contentToString())
}

fun qsort(array: IntArray) {
    qsort(array, 0, array.size - 1)
}

fun qsort(array: IntArray, left: Int, right: Int) {
    if (left >= right) {
        return
    }
    var pivotValue = array[(left + right) / 2]
    var currentLeft = left
    var currentRight = right
    while(currentLeft <= currentRight) {
        while (array[currentLeft] < pivotValue) {
            currentLeft++
        }
        while (array[currentRight] > pivotValue) {
            currentRight--
        }
        if (currentLeft <= currentRight) {
            swapElements(array, currentLeft, currentRight)
            currentLeft++
            currentRight--
        }
    }
    qsort(array, left, currentRight)
    qsort(array, currentLeft, right)
}

fun swapElements(array: IntArray, left: Int, right: Int) {
    val temp = array[left]
    array[left] = array[right]
    array[right] = temp
}

