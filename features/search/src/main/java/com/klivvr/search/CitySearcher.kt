package com.klivvr.search

import com.klivvr.search.model.CityUiModel
import kotlin.math.min

class CitySearcher(val sortedCities: List<CityUiModel>) {
    private val lowerNames = sortedCities.map { it.name.lowercase() }

    fun search(prefix: String): List<CityUiModel> {
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