package io.jasonlu.scalalearning.basic

import org.scalatest.FlatSpec

/**
  * Created by jiehenglu on 17/01/23.
  */
class StringTest extends FlatSpec {
  "string split and toSet" should "right" in {
    val firstSplit = "192.168.1.1:1000,192.168.1.2:1000".split(",")
    println(firstSplit)

    val secondSplit = firstSplit.toSet[String].map(_.split(":"))
    println(secondSplit)

    val result = secondSplit.map(x => (x(0), x(1).toInt))
    println(result)

    val expected = Set(("192.168.1.1", 1000), ("192.168.1.2", 1000))
    assert(result == expected)
  }
}
