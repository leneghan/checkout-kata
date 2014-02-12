package itv.checkout

/**
 * Created with IntelliJ IDEA.
 * User: andy
 * Date: 11/02/2014
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */
class Checkout(prices: Map[String, Int], multibuys: List[Multibuy] = List.empty) {

  def buy(items: List[String]): Int = {
    val total: Int = items.map(prices).sum
    val savings: Int = multibuys.map(_.calculateSaving(items)).sum
    total - savings
  }

}

case class Multibuy(itemSKU: String, quantity: Int, reduction: Int){
  def calculateSaving(items: List[String]): Int = {
    val count = items.count(_ == itemSKU)
    (count/quantity) * reduction
  }
}
