package main

import main.model.NormalFrame
import main.model.SpareFrame
import main.model.StrikeFrame
import main.util.getIntValue
import main.util.isSpare
import main.util.isStrike
import main.util.second

class ScoreParser {

    fun getScore(score: String): Int {
        var trimmedScore = score.replace(" ", "", true)
        var mainScore = trimmedScore.substringBefore("||")
        var extraBalls = trimmedScore.substringAfter("||")

        var framesList = mutableListOf<NormalFrame>()

        while (mainScore.isNotEmpty()) {
            val ball1 = mainScore.first()
            if (ball1.isStrike()) {
                var nextBall1: Char
                var nextBall2: Char
                if (mainScore.length <= 1) {
                    //last frame
                    nextBall1 = extraBalls.first()
                    nextBall2 = extraBalls.second()
                    framesList.add(StrikeFrame(nextBall1 = nextBall1.getIntValue(), nextBall2 = nextBall2.getIntValue()))
                    mainScore = mainScore.substringAfter('|', "")
                } else {
                    var nextBall1 = mainScore[2]
                    if (nextBall1.isStrike()) {
                        nextBall2 = if (mainScore.length <= 3) {
                            //last frame
                            extraBalls.first()
                        } else {
                            mainScore[4]
                        }
                        framesList.add(StrikeFrame(nextBall1 = nextBall1.getIntValue(), nextBall2 = nextBall2.getIntValue()))
                        mainScore = mainScore.substringAfter('|', "")
                    } else {
                        nextBall2 = mainScore[3]
                        if (nextBall2.isSpare()) {
                            framesList.add(StrikeFrame(nextBall1 = nextBall1.getIntValue(), nextBall2 = 10 - nextBall1.getIntValue()))
                        } else {
                            framesList.add(StrikeFrame(nextBall1 = nextBall1.getIntValue(), nextBall2 = nextBall2.getIntValue()))
                        }
                        mainScore = mainScore.substringAfter('|', "")
                    }
                }
            } else {
                val ball2 = mainScore.second()
                if (ball2.isSpare()) {
                    //Spare Play
                    var nextBall: Char = if (mainScore.length <= 2) {
                        //Last frame
                        extraBalls.first()
                    } else {
                        //NOT Last frame
                        mainScore[3]
                    }
                    framesList.add(SpareFrame(ball1.getIntValue(), 10 - ball1.getIntValue(), nextBall.getIntValue()))
                } else {
                    //Normal Play
                    framesList.add(NormalFrame(ball1.getIntValue(), ball2.getIntValue()))
                }
                mainScore = mainScore.substringAfter('|', "")
            }
        }

        //adds all frames points and return
        return framesList.sumBy { it.value }
    }
}

class ScoreParserReverse {

    fun getScore(score: String): Int {
        var trimmedScore = score.replace(" ", "", true)
        var mainScore = trimmedScore.substringBefore("||")
        var extraBalls = trimmedScore.substringAfter("||")

        var frameArray = arrayOfNulls<NormalFrame>(10)
        var currentPosition = frameArray.size - 1

        while (currentPosition >= 0) {
            var lastFrame = mainScore.substringAfterLast("|")
            mainScore = mainScore.substringBeforeLast("|")

            when {
                lastFrame[0].isStrike() -> {
                    if (currentPosition == 9) {// checks if it's last frame and use extra balls
                        frameArray[currentPosition] = StrikeFrame(nextBall1 = extraBalls[0].getIntValue(),
                                nextBall2 = extraBalls[1].getIntValue())
                    } else {
                        //get next frame
                        var nextFrame = frameArray[currentPosition + 1]
                        if (nextFrame is StrikeFrame) {
                            if (currentPosition == 8) { // check if it's one before last frame and use extra balls
                                frameArray[currentPosition] = StrikeFrame(nextBall1 = nextFrame.ball1Value,
                                        nextBall2 = extraBalls[0].getIntValue())
                            } else {
                                //get next frame
                                frameArray[currentPosition] = StrikeFrame(nextBall1 = nextFrame.ball1Value, nextBall2 = frameArray[currentPosition + 2]?.ball1Value
                                        ?: 0)
                            }
                        } else {
                            //create strike with ball 1 and ball 2
                            frameArray[currentPosition] = StrikeFrame(nextBall1 = nextFrame?.ball1Value
                                    ?: 0, nextBall2 = nextFrame?.ball2Value ?: 0)
                        }
                    }
                }
                lastFrame[1].isSpare() -> {
                    if (currentPosition == 9) {// checks if it's last frame and use extra balls
                        val ball1Value = lastFrame[0].getIntValue()
                        frameArray[currentPosition] = SpareFrame(ball1Value,
                                10 - ball1Value, extraBalls[0].getIntValue())
                    } else {
                        //get next frame
                        val ball1Value = lastFrame[0].getIntValue()
                        frameArray[currentPosition] = SpareFrame(ball1Value,
                                10 - ball1Value, frameArray[currentPosition + 1]?.ball1Value
                                ?: 0)
                    }
                }
                else -> {
                    frameArray[currentPosition] = NormalFrame(lastFrame[0].getIntValue(), lastFrame[1].getIntValue())
                }
            }
            currentPosition--

        }

        return frameArray.sumBy { it?.value ?: 0 }
    }

}