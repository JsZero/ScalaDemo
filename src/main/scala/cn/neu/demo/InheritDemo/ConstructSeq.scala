package cn.neu.demo.InheritDemo

class Creature {
  val range: Int = 10
  val env: Array[Int] = new Array[Int](range)
}

class Ant extends Creature {
  override val range = 2
}

class Item(val description: String, val price: Double) {
  final override def equals(other: Any): Boolean = {
    other.isInstanceOf[Item] && {
      val that = other.asInstanceOf[Item]
      description == that.description && price == that.price
    }
  }
}

object ConstructSeq {
  def main(args: Array[String]): Unit = {
    val a = new Ant
    println(a.env.length, a.range)
    val c = new Creature
    println(c.env.length)
    val scores = Map(("Alice", 10), ("Bob", 3), ("Cindy", 8))
    scores.get("sda")
  }
}
