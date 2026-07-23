import kotlin.math.ceil
import kotlin.math.max

fun main() {

}

fun minEatingSpeed(piles: IntArray, h: Int): Int {
    var maxRightSpeed = piles[0]
    for (i in 1 .. piles.size - 1) {
        if (piles[i] > maxRightSpeed) {
            maxRightSpeed = piles[i]
        }
    }

    var leftMinSpeed = 1
    while (leftMinSpeed < maxRightSpeed) {
        var middleSpeed = (maxRightSpeed + leftMinSpeed) / 2
        var hoursSpent = 0
        for (i in piles.indices) {
            hoursSpent += ceil(piles[i] / middleSpeed.toDouble()).toInt()
        }
        if (hoursSpent <= h) {
            maxRightSpeed = middleSpeed
        } else {
            leftMinSpeed = middleSpeed + 1
        }
    }
    return leftMinSpeed
}

// return index
fun binarySearch(array: IntArray, value: Int): Int {
    var left = 0
    var right = array.size - 1
    while (left <= right) {
        val middle = (left + right) / 2
        if (array[middle] == value) {
            return middle
        } else if (array[middle] > value) {
            right = middle - 1
        } else {
            left = middle + 1
        }
    }
    return -1
}
