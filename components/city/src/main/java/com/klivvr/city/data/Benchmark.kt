/*
package com.klivvr.city

import kotlin.math.min


class CitySearcherBinarySearch(cities: List<CityDataModel>) {
    private val sortedCities = cities.sortedWith(
        compareBy({ it.name.lowercase() }, { it.countryCode })
    )
    private val lowerNames = sortedCities.map { it.name.lowercase() }

    fun search(prefix: String): List<CityDataModel> {
        if (prefix.isEmpty()) return sortedCities
        val prefixLower = prefix.lowercase()
        val start = findLowerBound(prefixLower)
        val end = findUpperBound(prefixLower)
        return sortedCities.subList(start, end)
    }

    private fun findLowerBound(prefix: String): Int {
        var low = 0
        var high = sortedCities.size
        while (low < high) {
            val mid = (low + high) / 2
            if (comparePrefix(lowerNames[mid], prefix) < 0) low = mid + 1
            else high = mid
        }
        return low
    }

    private fun findUpperBound(prefix: String): Int {
        var low = 0
        var high = sortedCities.size
        while (low < high) {
            val mid = (low + high) / 2
            if (lowerNames[mid].startsWith(prefix) || comparePrefix(lowerNames[mid], prefix) <= 0) {
                low = mid + 1
            } else {
                high = mid
            }
        }
        return low
    }
    private fun comparePrefix(name: String, prefix: String): Int {
        val minLen = min(name.length, prefix.length)
        for (i in 0 until minLen) {
            val cmp = name[i].compareTo(prefix[i])
            if (cmp != 0) return cmp
        }
        return name.length.compareTo(prefix.length)
    }
}
class CitySearcherHashMap(cities: List<CityDataModel>) {
    private val prefixMap: Map<String, List<CityDataModel>>

    init {
        val map = mutableMapOf<String, MutableList<CityDataModel>>()
        cities.forEach { city ->
            val key = city.name.lowercase()
            for (i in 1..key.length) {
                val prefix = key.substring(0, i)
                map.getOrPut(prefix) { mutableListOf() }.add(city)
            }
        }
        prefixMap = map
    }

    fun search(prefix: String): List<CityDataModel> {
        return prefixMap[prefix.lowercase()] ?: emptyList()
    }
}
class CitySearcherSortedHashMap(cities: List<CityDataModel>) {
    private val prefixMap: Map<String, List<CityDataModel>>

    init {
        val map = mutableMapOf<String, MutableList<CityDataModel>>()
        cities.forEach { city ->
            val key = city.name.lowercase()
            for (i in 1..key.length) {
                val prefix = key.substring(0, i)
                map.getOrPut(prefix) { mutableListOf() }.add(city)
            }
        }
        prefixMap = map
    }

    fun search(prefix: String): List<CityDataModel> {
        val results = prefixMap[prefix.lowercase()] ?: emptyList()
        // Sort the results by name then countryCode on-demand
        return results.sortedWith(compareBy({ it.name.lowercase() }, { it.countryCode }))
    }
}

class CitySearcherTreeMap(cities: List<CityDataModel>) {

    val sortedCities = cities.sortedWith(compareBy({ it.name.lowercase() }, { it.countryCode }))
    // Step 2: Group by lowercase name (keys)
    val grouped = sortedCities.groupBy { it.name.lowercase() }
    // Step 3: No need to sort groups because sortedCities is already sorted by country inside each group
    val sortedMap = com.klivvr.search.CitySearcherTreeMap.grouped.toSortedMap(String.CASE_INSENSITIVE_ORDER)

    fun search(prefix: String): Collection<Any?> {
        if (prefix.isEmpty()) return com.klivvr.search.CitySearcherTreeMap.sortedMap.values.flatten()
        val prefixLower = prefix.lowercase()
        return com.klivvr.search.CitySearcherTreeMap.sortedMap
            .subMap(prefixLower, prefixLower + "\uffff")
            .values
//            .flatten()
    }
}
fun benchmarkBinary(cities: List<CityDataModel>,prefix: String) {
    // HashMap approach
    val citySearcherBinarySearch = CitySearcherBinarySearch(cities)
    measure("Binary $prefix search") {
        citySearcherBinarySearch.search(prefix)
    }
}
fun benchmarkTree(cities: List<CityDataModel>,prefix: String) {
    // HashMap approach
    val citySearcherTreeMap = com.klivvr.search.CitySearcherTreeMap(cities)
    measure("Tree $prefix search") {
        com.klivvr.search.CitySearcherTreeMap.search(prefix)
    }
}
fun benchmarkHashMap(cities: List<CityDataModel>,prefix: String) {
    // HashMap approach
    val hashMapSearcher = CitySearcherHashMap(cities)
    measure("HashMap $prefix search") {
        hashMapSearcher.search(prefix)
    }
}
fun benchmarkSortedHashMap(cities: List<CityDataModel>,prefix: String) {

    // HashMap Sorted after search approach
    val citySearcherSortedHashMap = CitySearcherSortedHashMap(cities)
    measure("HashMap Sorted $prefix search") {
        citySearcherSortedHashMap.search(prefix)
    }
}

fun measure(tag: String, block: () -> Unit) {
    val start = System.nanoTime()
    block()
    val duration = (System.nanoTime() - start) / 1e6
    println("$tag: ${"%.3f".format(duration)} ms")
}*/
