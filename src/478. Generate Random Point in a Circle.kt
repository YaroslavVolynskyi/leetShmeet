import kotlin.random.Random

fun main() {
    val s = Solution(1.0, 0.0, 0.0 )
    for (i in 0 .. 4) {
        println(s.randPoint().contentToString())
    }
}

class Solution(val radius: Double, val x_center: Double, val y_center: Double) {

    fun randPoint(): DoubleArray {
        var nextX: Double
        var nextY: Double
        while (true) {
            nextX = Random.nextDouble(from = x_center - radius, until = x_center + radius)
            nextY = Random.nextDouble(from = y_center - radius, until = y_center + radius)
            if (isInCircle(nextX, nextY)) {
                return doubleArrayOf(nextX, nextY)
            }
        }
    }

    private fun isInCircle(x: Double, y: Double): Boolean {
        val distance = Math.sqrt(
            Math.pow(Math.abs(x - x_center), 2.0) + Math.pow(Math.abs(y - y_center), 2.0)
        )
        return distance <= radius
    }

}