package mindist

fun main() {

    println(
        getMinDistSum(arrayOf(
            intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(1, 2), intArrayOf(2,1)
        ))
    )
}

fun getMinDistSum(positions: Array<IntArray>): Double {
    var currentCenterX = 0.0
    var currentCenterY = 0.0
    positions.forEach { position ->
        currentCenterX += position[0]
        currentCenterY += position[1]
    }
    currentCenterX /= positions.size
    currentCenterY /= positions.size
    var currentStep = 0.5
    var minDist = Double.MAX_VALUE
    while (currentStep > Math.pow(10.0, -5.0)) {
        val currentDistList = listOf(
            distanceSum(positions, currentCenterX + currentStep, currentCenterY),
            distanceSum(positions, currentCenterX - currentStep, currentCenterY),
            distanceSum(positions, currentCenterX, currentCenterY + currentStep),
            distanceSum(positions, currentCenterX, currentCenterY - currentStep),
        )
        val currentMin = currentDistList.min()
        val index = currentDistList.indexOf(currentMin)
        if (currentMin < minDist) {
            minDist = currentMin
            when (index) {
                0 -> currentCenterX += currentStep
                1 -> currentCenterX -= currentStep
                2 -> currentCenterY += currentStep
                3 -> currentCenterY -= currentStep
            }
        } else {
            currentStep /= 2
        }
    }
    return minDist
}

fun distanceSum(positions: Array<IntArray>, x: Double, y: Double): Double {
    var sum = 0.0
    positions.forEach { position ->
        sum += Math.sqrt(
            Math.pow(x - position[0], 2.0) + Math.pow(y - position[1], 2.0)
        )
    }
    return sum
}