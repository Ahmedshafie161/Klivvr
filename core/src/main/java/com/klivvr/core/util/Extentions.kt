package com.klivvr.core.util

fun <T> List<T>.groupByFirstLetter(selector: (T) -> String): Map<Char, List<T>> {
    return this
        .groupBy { selector(it).first().uppercaseChar() }
        .toSortedMap()
}
