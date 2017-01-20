package io.jasonlu.scalalearning.basic

import org.scalatest.FlatSpec

/**
  * Created by jiehenglu on 17/01/17.
  */
class ForLoopTest extends FlatSpec {
  "simple int range yield" should "produce range" in {
    val r = 1 to 10
    println(r)

    val expected: Range = Range.inclusive(1, 10)
    assert(r === expected)
  }

  "simple int loop yield" should "produce range" in {
    val r = for (i <- 1 to 10) yield i
    println(r)

    val expected: Range = Range.inclusive(1, 10)
    assert(r === expected)
  }
}
