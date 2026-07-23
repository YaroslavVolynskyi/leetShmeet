import java.util.*
import kotlin.math.max

fun main() {
    val merged = merge(arrayOf(
        intArrayOf(8,10), intArrayOf(1,3), intArrayOf(2,6), intArrayOf(15,18)
    ))
    merged.forEach {
        println(it.contentToString())
    }
}


fun merge(intervals: Array<IntArray>): Array<IntArray> {
    Arrays.sort(intervals, Comparator { a: IntArray, b: IntArray -> a[0].compareTo(b[0]) })
    val list = mutableListOf<IntArray>()
    list.add(intervals[0])
    for (i in 1 .. intervals.size - 1) {
        if (intervals[i][0] <= list.last()[1]) {
            list.last()[1] = max(intervals[i][1], list.last()[1])
        } else {
            list.add(intervals[i])
        }
    }

    return list.toTypedArray()
}