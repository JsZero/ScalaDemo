package cn.neu.demo

import scala.collection.mutable.ArrayBuffer

class NetWork {

  class Member(val name: String) {
    val contacts = new ArrayBuffer[Member]
  }

  private val members = new ArrayBuffer[Member]

  def join(name: String): NetWork.this.Member = {
    val m = new Member(name)
    members += m
    m
  }
}

object NestedClassDemo {
  def main(args: Array[String]): Unit = {
    // 在Java中内部类从属于外部类，在Scala中每个实例都有自己的内部类
    // 如果不希望这样做，那么可以
    // 1.把嵌套类移到伴生对象中
    // 2.使用类型投影，val contacts = new ArrayBuffer[NetWork#Member]，表示“任何NetWork的Member”
    val chatter = new NetWork
    val myFace = new NetWork
    val fred = chatter.join("Fred") // fred类型是chatter.Member，其contact存储的也是chatter.Member类型
    val wilma = chatter.join("Wilma") // wilma类型是chatter.Member，其contact存储的也是chatter.Member类型
    fred.contacts += wilma
    val barney = myFace.join("Barney") // wilma类型是myFace.Member，其contact存储的也是myFace.Member类型
    // fred.contacts += barney // 编译错误，chatter.Member和myFace.Member不匹配
  }
}
