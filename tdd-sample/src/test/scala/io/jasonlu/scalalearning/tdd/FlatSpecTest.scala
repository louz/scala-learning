package io.jasonlu.scalalearning.tdd

import org.scalatest.FlatSpec

/**
  * Created by jiehenglu on 17/01/13.
  */
class FlatSpecTest extends FlatSpec {
  "empty set" should "have size 0" in {
    assert(Set.empty.size == 1)
  }
}
