package io.jasonlu.scalalearning.basic

import org.scalatest.FlatSpec

/**
  * Created by jiehenglu on 17/01/13.
  */
class OptionTest extends FlatSpec{

  "empty option string" should "return empty string" in {
    val o: Option[Bean] = Option.empty
    assert(o.getOrElse(new Bean("a")).name == "bean1")
  }
}
