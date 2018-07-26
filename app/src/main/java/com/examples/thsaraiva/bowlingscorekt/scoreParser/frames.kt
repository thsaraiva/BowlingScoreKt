package main.model

open class NormalFrame(open var ball1Value: Int, open var ball2Value: Int) {
    open val value: Int
        get() = ball1Value + ball2Value
}

class StrikeFrame(override var ball1Value: Int = 10,
                  override var ball2Value: Int = 0,
                  private var nextBall1: Int,
                  private var nextBall2: Int) : NormalFrame(ball1Value, ball2Value) {
    override val value: Int
        get() = super.value + nextBall1 + nextBall2
}

class SpareFrame(override var ball1Value: Int,
                 override var ball2Value: Int,
                 private var nextBall1: Int) : NormalFrame(ball1Value, ball2Value) {
    override val value: Int
        get() = super.value + nextBall1
}