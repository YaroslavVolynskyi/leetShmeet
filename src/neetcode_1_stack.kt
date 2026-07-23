import kotlin.math.min

fun main() {
//    println(isValid("([{}])"))
//    println(isValid("(([[{}{}]]()))"))

//    val minStack = MinStack()
//    minStack.push(1)
//    minStack.push(2)
//    minStack.push(0)
//    minStack.getMin2() // return 0
//    minStack.pop()
//    minStack.top() // return 2
//    minStack.getMin2() //

//    val input = arrayOf("1","2","+","3","*","4","-")
//    println(evalRPN(input))

//    println(dailyTemperatures(intArrayOf(30,38,30,36,35,40,28)).contentToString())
//    println(largestRectangleArea(intArrayOf(7,1,7,2,2,4)))
    println(carFleet(target = 10, position = intArrayOf(1,4), speed = intArrayOf(3,2)))
    println(carFleet(target = 10, position = intArrayOf(4,1,0,7), speed = intArrayOf(2,2,1,1)))
}

/**
 * https://neetcode.io/problems/car-fleet/solution
 *
 */
fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
    val array = Array(position.size) { i ->
        position[i] to speed[i]
    }.sortedByDescending { it.first }
    val times = DoubleArray(position.size) { i ->
        (target - array[i].first).toDouble() / array[i].second
    }
    var distinctsTimes = arrayListOf<Double>()
    for (time in times) {
        if (distinctsTimes.isEmpty()) {
            distinctsTimes.add(time)
        } else {
            if (time > distinctsTimes.last()) {
                distinctsTimes.add(time)
            }
        }
    }

    return distinctsTimes.size
}

/**
 * https://neetcode.io/problems/largest-rectangle-in-histogram/solution
 * 0,1,2,3
 * 9,9,7,9, 2, 4,2, 7
 */
fun largestRectangleArea(heights: IntArray): Int {
    val areas = IntArray(heights.size)
    for (i in areas.indices) {
        // to the left
        var mostLeft = i
        while(mostLeft > 0 && heights[mostLeft - 1] >= heights[i]) {
            mostLeft--
        }
        //to the right
        var mostRight = i + 1
        while (mostRight < heights.size && heights[mostRight] >= heights[i]) {
            mostRight++
        }
        areas[i] = heights[i] * (mostRight - mostLeft)
    }
    return areas.max()
}

/**
 * https://neetcode.io/problems/daily-temperatures/question?list=neetcode150
 * Input: temperatures = [30,38,30,36,35,40,28]
 * Output: [1,4,1,2,1,0,0]
 */
fun dailyTemperatures(temperatures: IntArray): IntArray {
    var resArray = IntArray(temperatures.size)
    for (i in 0 .. temperatures.size - 1) {
        var j = i + 1
        while (j < temperatures.size && temperatures[j] <= temperatures[i]) {
            j++
        }
        resArray[i] = if (j == temperatures.size) 0 else j - i
    }

    return resArray
}

/**
 * https://neetcode.io/problems/evaluate-reverse-polish-notation/question?list=neetcode150
 * Input: tokens = ["1","2","+","3","*","4","-"]
 * Output: 5
 * Explanation: ((1 + 2) * 3) - 4 = 5
 */
fun evalRPN(tokens: Array<String>): Int {
    val tokenStack = TokenStack()
    tokens.forEach { token ->
        val tokenValue = token.toIntOrNull()
        if (tokenValue != null) {
            tokenStack.push(tokenValue)
        } else {
            val operand2 = tokenStack.pop()
            val operand1 = tokenStack.pop()
            when (token) {
                "+" -> {
                    tokenStack.push(operand2 + operand1)
                }
                "-" -> {
                    tokenStack.push(operand1 - operand2)
                }
                "*" -> {
                    tokenStack.push(operand2 * operand1)
                }
                "/" -> {
                    tokenStack.push(operand1 / operand2)
                }
            }
        }
    }
    return tokenStack.pop()
}

class TokenStack {
    var head: TokenNode? = null

    fun push(token: Int) {
        if (head == null) {
            head = TokenNode(token)
        } else {
            val tokenNode = TokenNode(token)
            tokenNode.next = head
            head = tokenNode
        }
    }

    fun pop(): Int {
        val value = head?.value ?: 0
        head = head?.next
        return value
    }
}

class TokenNode(val value: Int) {
    var next: TokenNode? = null
}


/**
 * https://neetcode.io/problems/minimum-stack/question?list=neetcode150
 */
class MinStack() {

    var head: Node? = null

    fun push(`val`: Int) {
        val min = min(`val`, head?.min ?: Int.MAX_VALUE)
        if (head == null) {
            head = Node(`val`)
        } else {
            val newHead = Node(`val`)
            newHead.next = head
            head = newHead
        }
        head?.min = min
    }

    fun pop() {
        head = head?.next
    }

    fun top(): Int {
        return head?.value ?: 0
    }

    fun getMin2(): Int {
        return head?.min ?: Int.MAX_VALUE
    }

    class Node(val value: Int) {
        var next: Node? = null
        var min: Int? = null
    }
}


/**
 * https://neetcode.io/problems/validate-parentheses/question?list=neetcode150
 */
fun isValid(s: String): Boolean {
    var charList = mutableListOf<Char>()
    var map = mutableMapOf('}' to '{', ']' to '[', ')' to '(')
    for (c in s) {
        if (c == '{' || c == '(' || c == '[') {
            charList.add(c)
        } else {
            val fromMap = map[c]
            if (charList.isEmpty()) {
                return false
            }
            if (charList.last() == fromMap) {
                charList.removeLast()
            } else {
                return false
            }
        }
    }
    return charList.isEmpty()
}