package main.util

import java.util.*

fun Char.isMiss() = this.equals('-', true)

fun Char.isStrike() = this.equals('X', true)

fun Char.isSpare() = this.equals('/', true)

fun String.second(): Char {
    if (isEmpty())
        throw NoSuchElementException("Char sequence is empty.")
    return this[1]
}

fun Char.getIntValue(): Int {
    return when {
        this in '1'..'9' -> this.toInt() - '0'.toInt()
        this.isMiss() -> 0
        this.isStrike() -> 10
        else -> throw InvalidPropertiesFormatException("This character has no Int value")
    }
}

fun <T, R> Collection<T>.accumulate(
        initial: R,
        combine: (acc: R, nextElement: T, nextElementPosition: Int) -> R
): R {
    var accumulator: R = initial
    for (i in 0 until this.size) {
        accumulator = combine(accumulator, this.elementAt(i), i)
    }
    return accumulator
}