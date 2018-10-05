package cn.neu.demo.CommonDemo

import scala.collection.mutable._

object Test {
  def main(args: Array[String]): Unit = {
    val v0 = Array((1, 2), (2, 4), (2, 7), (3, 2))
    //    val res1 = res0.groupBy(_._1)
    v0.foldRight(scala.collection.mutable.Map[Int, Set[Int]]())((x: (Int, Int), m: scala.collection.mutable.Map[Int, Set[Int]]) => {
      if (m.contains(x._1)) m(x._1) = m(x._1) + x._2
      else m(x._1) = Set(x._2)
      m
    })
    val str = "etqw\tuie\tuiqw\t"
    val strs = str.split("\t")
    println(strs.length)
  }
}
