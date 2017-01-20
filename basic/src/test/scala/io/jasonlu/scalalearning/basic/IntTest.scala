package io.jasonlu.scalalearning.basic

import org.scalatest.FlatSpec

/**
  * Created by jiehenglu on 17/01/18.
  */
class IntTest extends FlatSpec {
  "1 until 1" should "no result" in {
    val result = 1.until(1)
    println(result)

    assert(result == Range.inclusive(1, 0))
  }
}
