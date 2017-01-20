package io.jasonlu.scalalearning.basic

import org.scalatest.FlatSpec

/**
  * Created by jiehenglu on 17/01/19.
  */
class CollectionTest extends FlatSpec {
  "empty list's min" should "0" in {
    val list = List[Int]()
    assertThrows[UnsupportedOperationException](list.min)
  }
}
