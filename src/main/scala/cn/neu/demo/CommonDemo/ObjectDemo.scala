package cn.neu.demo.CommonDemo

/*
* ·用对象作为单例或存放工具方法
* ·类可以拥有一个同名的伴生对象
* ·对象可以扩展类或者特质
* ·对象的apply方法通常用来构造伴生类的新实例
* ·如果不想显式定义main方法，可以用扩展App特质的对象
* ·你可以通过扩展Enumeration对象来实现枚举
* */

class Account { // 伴生类
  val id = Account.newUniqueNumber()
  private var balance = 0.0

  def deposit(amount: Double) {
    balance += amount
  }
}

/*
  * 对象的构造器在该对象第一次被使用的时候调用。如果从未被使用，其构造器也不会被执行
  * 对象本质上可以拥有类的所有特性——它甚至可以扩展其他类或者特质
  * 对象和类的唯一不同在于：不能对对象提供构造函数
  * */
object Account { // 伴生对象
// 伴生对象的功能特性不在类的作用域内。在Account类中需要使用Account.的方法调用伴生对象中的方法
  private var lastNumber = 0

  def newUniqueNumber(): Int = {
    lastNumber += 1
    lastNumber
  }
}

object ObjectDemo {
  def main(args: Array[String]): Unit = {

  }
}
