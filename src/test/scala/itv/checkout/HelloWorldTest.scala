package itv.checkout

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class HelloWorldTest extends FunSuite with ShouldMatchers{

  val pricelist = Map("A" -> 50, "B" -> 30)
  val savings = List(Multibuy("A", 3, 20), Multibuy("B", 2, 15))
  val checkout = new Checkout(pricelist)
  val multibuyCheckout = new Checkout(pricelist, savings)

  test("when buying an item costing 50 the total should be 50") {
    val total = checkout.buy(List("A"))
    total should equal(50)
  }

  test("when buying an item costing 30 the total should be 30") {
    val total = checkout.buy(List("B"))
    total should equal(30)
  }

  test("when buying three items costing 30 the total should be 90") {
    val total = checkout.buy(List("B", "B", "B"))
    total should equal(90)
  }

  test("when buying two items costing 30 and one costing 50 the total should be 110") {
    val total = checkout.buy(List("B", "A", "B"))
    total should equal(110)
  }

  test("when buying three items with a multibuy of 130 the total should be 130") {
    val total = multibuyCheckout.buy(List("A", "A", "A"))
    total should equal(130)
  }

  test("when buying 10 items with a multibuy of 3 for 130 the total should be 440") {
    val total = multibuyCheckout.buy(List("A", "A","A", "A", "A", "A", "A", "A", "A", "A"))
    total should equal(440)
  }

  test("when purchase qualifies for multiple discounts then all are applied") {
    val total = multibuyCheckout.buy(List("A", "A", "A", "A", "B", "B", "B", "B", "B"))
    total should equal(300)
  }
}
