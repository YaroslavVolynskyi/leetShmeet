package l721

/**
 * https://leetcode.com/problems/accounts-merge/description/
 */

fun main() {
    println(
        accountsMerge(
            accounts = listOf(
                listOf("John", "john3@mail.com", "johnsmith@mail.com", "john_newyork@mail.com"),
                listOf("John", "john00@mail.com", "johnsmith@mail.com"),
                listOf("Mary", "mary@mail.com"),
                listOf("Mary", "johnnybravo@mail.com", "mary@mail.com")
            )
        )
    )
    println()
}

fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
    val set = mutableSetOf<String>()
    val namesMap = mutableMapOf<String, String>()
    accounts.forEach { list ->
        for (i in 1 .. list.size - 1) {
            set.add(list[i])
            namesMap[list[i]] = list[0]
        }
    }
    val parents = mutableMapOf<String, String>()
    set.forEach {
        parents[it] = it
    }
    accounts.forEach { account ->
        for (i in 1 .. account.size - 2) {
            val parent1 = findParent(parents,account[i])
            val parent2 = findParent(parents, account[i + 1])
            if (parent1 != parent2) {
                parents[parent2] = parent1
            }
        }
    }
    parents.keys.forEach { key ->
        val root = findParent(parents, key)
        parents[key] = root
    }
    val result = mutableListOf<MutableList<String>>()
    val map = mutableMapOf<String, MutableList<String>>()
    parents.forEach { (key, value) ->
        map.getOrPut(value) { mutableListOf() }.add(key)
    }
    map.forEach { (root, emailsList) ->
        var list = mutableListOf<String>()
        list.add(namesMap[root]!!)
        list.addAll(emailsList.sorted())
        result.add(list)
    }

    return result
}

private fun findParent(parents: MutableMap<String, String>, email: String): String {
    parents.getOrPut(email) { email }
    var current = email
    while(parents[current] != current) {
        current = parents[current]!!
    }
    return current
}
