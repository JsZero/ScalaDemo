package cn.neu.demo

import scala.collection.mutable.ArrayBuffer

object ArrayDemo {
  def main(args: Array[String]): Unit = {

    // 在Scala中长度不变的数组用Array，Array以Java数组的方式实现
    // 1、创建数组
    // 第一种方式使用new关键字创建，括号里面的数字不能为空，泛型也应该有，没有的话数组会被初始化为Nothing
    val nums = new Array[Int](10) // output: Array[Int] = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    // 第二中方式不使用new关键字创建，括号里面是数组的初始值，没有的话就是空数组
    val a = Array("Hello", "World") // output: a: Array[String] = Array(Hello, World)
    // 2、数组元素的取用
    println(a(0)) // output: Hello

    // 在Scala中变长数组用ArrayBuffer，相当于java中的ArrayList、C++中的vector
    // 1、创建动态数组
    // 第一种方式不使用new关键字，泛型可以根据后面括号里的内容推断，如果未定义泛型且括号里没有，
    // 泛型会被推断成Nothing，括号里面是存储在新数组里面的元素序列，可以没有
    val b = ArrayBuffer(10) // output: b: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(10)
    // 第二种方式使用new关键字，应该使用泛型，不使用泛型默认是Nothing，
    // 可以在后面加括号，里面写数字，但貌似没什么卵用
    val c = new ArrayBuffer[Int] // output: c: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()
    // 2、动态数组的存取
    // 以下是在ArrayBuffer尾端增删操作，这样做是高效的
    // +=在尾端添加元素
    b += 1; // output: ArrayBuffer(10, 1)
    // +=在尾端添加多个元素
    b += (1, 2, 3, 5) // output: ArrayBuffer(10, 1, 1, 2, 3, 5)
    // ++=在尾端追加任何集合
    b ++= Array(8, 13, 21) // output: ArrayBuffer(10, 1, 1, 2, 3, 5, 8, 13, 21)
    // trimEnd移除最后面n个元素
    b.trimEnd(5) // output: ArrayBuffer(10, 1, 1, 2)
    // 以下是在指定位置增删元素的操作，这种操作是不高效的，因为之后的元素存在平移问题耗费时间
    // 在下标2处插入，原下标2及之后的元素后移
    b.insert(2, 6) // output: ArrayBuffer(10, 1, 6, 1, 2)
    // 在下标2处插入（7，8，9）三个元素，原位置元素后移
    b.insert(2, 7, 8, 9) // output: ArrayBuffer(10, 1, 7, 8, 9, 6, 1, 2)
    // 移除下标为2的元素
    b.remove(2) // output: ArrayBuffer(10, 1, 8, 9, 6, 1, 2)
    // 从下标为2处的元素开始，移除三个元素
    b.remove(2, 3) // output: ArrayBuffer(10, 1, 1, 2)
    // 3、ArrayBuffer数组转换为Array
    b.toArray // output: Array(10, 1, 1, 2)
  }
}
