import kotlin.random.Random

fun main() {
    val rs = RandomizedSet()
    rs.apply {
        for (i in 0 .. 6) {
            insert(i)
        }
        remove(3)
    }

    println()
}

class RandomizedSet() {

    val array = ArrayList<Int>()
    val map = hashMapOf<Int, Int>()

    fun insert(`val`: Int): Boolean {
        if (map.contains(`val`)) {
            return false
        }
        map[`val`] = array.size
        array.add(`val`)
        return true
    }

    fun remove(`val`: Int): Boolean {
        if (!map.contains(`val`)) {
            return false
        }
        val indexToRemove = map[`val`]!!
        // swap element to Remove with last one
        map[array[array.size - 1]] = indexToRemove
        array[indexToRemove] = array[array.size - 1]
        array.removeAt(array.size - 1)
        map.remove(`val`)
        return true
    }

    fun getRandom(): Int {
        return array[Random.nextInt(from = 0, until = array.size)]
    }

}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * var obj = RandomizedSet()
 * var param_1 = obj.insert(`val`)
 * var param_2 = obj.remove(`val`)
 * var param_3 = obj.getRandom()
 */