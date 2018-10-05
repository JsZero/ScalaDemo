package cn.neu.demo.CollectionDemo

import scala.collection.SortedSet
import scala.collection.mutable.ArrayBuffer

object CollectionOverview {
  def main(args: Array[String]): Unit = {
    // Seq是一个有先后次序的值的序列，比如数组或者列表
    // Set是一组没有先后次序的值
    // Map是一组(Key, Value)对偶。SortedMap按照键的排序访问其中的实体
    Iterable("as", "sd", "df")
    Set("as", "sd", "df")
    Map(1 -> "sd", 2 -> "ad", 3 -> "ds")
    SortedSet("Hello", "World")
    val coll = Seq(1, 1, 2, 3, 5, 8, 13)
    val set = coll.toSet
    val buffer = coll.to[ArrayBuffer]
  }
}
