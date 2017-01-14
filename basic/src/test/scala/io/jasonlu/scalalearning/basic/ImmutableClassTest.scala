package io.jasonlu.scalalearning.basic

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by jiehenglu on 17/01/13.
  */
class ImmutableClassTest extends FlatSpec with Matchers {
  "No arg constructor" should "has the default value" in {
    val c = new ImmutableClass
    assert(c.name == "defaultName")
    assert(c.value == 1)
  }

  "通过指定参数创建对象时，未指定的参数" should "为默认值" in {
    val c = new ImmutableClass(value = 2)
    assert(c.name == "defaultName")
    c.value shouldBe (1)
  }
}
