package com.examples.thsaraiva.roomwordsamplekt

import io.reactivex.Observable
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun test() {
        val lastLine = "14|X|4/|25|-7|X|X|26|-1|9/"
        val lastLineSplit = lastLine.split(delimiters = "|", ignoreCase = true)
        Observable.fromIterable(lastLineSplit)
                .map {
                    when (it.length) {
                        1 -> Play(it[0], '0')
                        2 -> Play(it[0], it[1])
                        else -> Play('-', '-')
                    }
                }
//                .flatMap { frame -> Observable.just(frame.pointsBall1, frame.pointsBall2) }
                .scan { last: Play, next: Play -> next + last }
                .subscribe {
                    println(it)
                }

    }
}

class Play(ball1: Char, ball2: Char) {
    val isStrike: Boolean = ball1.equals('X', true)
    val isSpare: Boolean = ball2.equals('/', true)
    var pointsBall1 = getBallIntValue(ball1)
    var pointsBall2 = getBallIntValue(ball2)
    val totalPoints = pointsBall1 + pointsBall2

    private fun getBallIntValue(ballCharValue: Char): Int {
        val tempVal = pointsBall1
        return when (ballCharValue) {
            'X' -> 10
            '/' -> 10 - tempVal
            in '1'..'9' -> ballCharValue.toInt() - '0'.toInt()
            else -> 0
        }
    }

    override fun toString(): String {
        return when {
            isStrike -> "Strike($pointsBall1,$pointsBall2)"
            isSpare -> "Spare($pointsBall1,$pointsBall2)"
            else -> "Play($pointsBall1,$pointsBall2)"
        }
    }

    operator fun plus(lastPlay: Play): Play {
        return when {
            lastPlay.isStrike -> {
                this.pointsBall1 += this.pointsBall1
                this.pointsBall2 += this.pointsBall2
                this
            }
            lastPlay.isSpare -> {
                this.pointsBall1 += this.pointsBall1
                this
            }
            else -> this
        }
    }
}

