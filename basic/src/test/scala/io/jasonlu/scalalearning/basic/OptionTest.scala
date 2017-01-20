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

  "None's map" should "None" in {
    val noneOption: Option[String] = Option(null)
    val result = noneOption.map(x => s"hello[$x]")

    println(s"noneOption after map fun:$result")
    assert(result == None)
  }

  "Some's map" should "be Some" in {
    val valueOption: Option[String] = Option("value1")
    val valueResult = valueOption.map(x => s"hello[$x]")
    println(s"valueResult after map fun:$valueResult")
    assert(valueResult == Option("hello[value1]"))
  }
}
