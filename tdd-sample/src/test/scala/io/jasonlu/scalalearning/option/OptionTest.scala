package io.jasonlu.scalalearning.option

import org.scalatest.FunSuite

/**
  * Created by jiehenglu on 17/01/16.
  */
class OptionTest extends FunSuite {
  test("List[Option] flatmap should return not None data") {
    val list: List[Option[String]] = List(Some("a"), Some("b"), None)

    val expectedA: List[String] = List("A", "B")
    val a = list.flatMap(_.map(_.toUpperCase()))
    assert(a == expectedA)

    val expectedB: List[String] = List("a", "b")
    val b = list.flatten
    assert(b == expectedB)
  }
}
