package cn.neu.demo.CommonDemo

import scala.collection.mutable.{ArrayBuffer, Buffer}
import scala.math._

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

    // ArrayBuffer和Array的相互转换
    // ArrayBuffer数组转换为Array
    b.toArray // output: Array(10, 1, 1, 2)
    // Array数组转换为ArrayBuffer
    a.toBuffer // output: ArrayBuffer(Hello, World)

    // Array和ArrayBuffer的遍历
    // 在Scala中数组和变长数组的遍历是一样的，不像java和C++会有所不同
    // 顺序遍历每个元素（Ps. until和to相似，但是排除了最后一个元素）
    for (i <- 0 until b.length)
      print(s"$i:${b(i)}${if (i != b.length - 1) "," else ""}") // output: 0:10,1:1,2:1,3:2
    println()
    for (i <- b.indices)
      print(s"$i:${b(i)}${if (i != b.length - 1) "," else ""}") // output: 0:10,1:1,2:1,3:2
    println()
    // 顺序遍历元素，每两个一跳
    for (i <- 0 until b.length by 2)
      print(s"$i:${b(i)}${if (i != ceil(b.length / 2)) "," else ""}") // output: 0:10,2:1
    println()
    for (i <- b.indices by 2)
      print(s"$i:${b(i)}${if (i != ceil(b.length / 2)) "," else ""}") // output: 0:10,2:1
    println()
    //逆序遍历元素
    for (i <- b.indices.reverse)
      print(s"$i:${b(i)}${if (i != 0) "," else ""}") // output: 0:10,1:1,2:1,3:2
    println()
    // 快学Scala上说是逆序访问，我尝试了2.11.11和2.12.6版本的Scala发现并不是，结果为空
    // for (i <- 0 until b.length by -1)
    //   print(s"$i:${b(i)}${if (i != b.length - 1) "," else ""}")
    // println()
    // 当不需要使用下标的时候我们可以直接访问数组元素，
    // 类似于Java中的增强for循环、c++中的“基于区间的”for循环
    for (elem <- b)
      print(s"${elem} ") // output: 10 1 1 2
    println()

    // 数组转换
    // 数组转换是指从一个数组（或者数组缓冲出发），通过某种规则转换出新的数组（数组缓冲），而不改变原数组（数组缓冲）
    val d = Array(2, 3, 5, 7, 11)
    // 场景：找到该数组中的奇数，并翻倍生成新数组
    // 第一种方式是使用 for/ yield 循环（yield 出来的集合类型跟遍历的集合类型一致，每次迭代增加一个元素）
    val d1 = for (elem <- d if elem % 2 != 0) yield 2 * elem // output: Array(6, 10, 14, 22)
    // 第二种方式是使用 filter 和 map 操作
    val d2 = d.filter(_ % 2 != 0).map(2 * _) // output: Array(6, 10, 14, 22)
    // 第二种方式也可以写成这样 val d3 = d filter{_ % 2 != 0} map{2 * _}
    // 当我们有这样一种情况，要从一个整数数组缓冲中移除所有的负数
    // 第一印象的做法是这样，
    var n = b.length
    var i = 0
    while (i < n) {
      if (b(i) >= 0) i += 1
      else {
        b.remove(i)
        n -= 1
      }
    }
    // 注意在移除元素的时候不递增 i 而是递减 n ，但是这种做法的缺点就是移动了后面要删除的元素，效率比较低
    // 正确的做法1.在新数组中记录要移除的元素，然后按顺序从后往前删除
    val positionToRemove = for (i <- b.indices if b(i) < 0) yield i
    for (i <- positionToRemove.reverse) b.remove(i)
    // 正确的做法2.（推荐）记录所有满足条件不需要移除的元素位置，将这些元素向前赋值堆叠，然后把尾部截断即可
    val positionToKeep = for (i <- b.indices if b(i) >= 0) yield i
    for (i <- positionToKeep.indices) b(i) = b(positionToKeep(i))
    b.trimEnd(b.length - positionToKeep.length)

    // 常用函数/方法
    // sum 函数：求和函数，要求数组或者数组缓冲的元素类型必须是数值类型
    val e = Array(1, 7, 2, 9)
    val f = ArrayBuffer(1, 7, 2, 9)
    println(e.sum) // output: 19
    println(f.sum) // output: 19
    // min/max 函数：求最小/最大值，要求元素必须支持比较操作（即，带有 Ordered 特质）
    println(e.min) // output: 1
    println(e.max) // output: 9
    println(f.min) // output: 1
    println(f.max) // output: 9
    // sorted 方法：将数组或者数组缓冲排序并返回排序后的数组或者数组缓冲，不会修改原始版本
    println(e.sorted.mkString("<", ",", ">")) // output:<1,2,7,9>
    println(e.mkString("<", ",", ">")) // output:<1,7,2,9>
    println(f.sorted.mkString("<", ",", ">")) // output:<1,2,7,9>
    println(f.mkString("<", ",", ">")) // output:<1,7,2,9>
    // sortWith 方法：使用自定义的比较函数对元素进行比较，同样不改变原数组/数组缓冲
    println(e.sortWith(_ > _).mkString("<", ",", ">")) // output:<9,7,2,1>
    println(e.mkString("<", ",", ">")) // output:<1,7,2,9>
    println(f.sortWith(_ > _).mkString("<", ",", ">")) // output:<9,7,2,1>
    println(f.mkString("<", ",", ">")) // output:<1,7,2,9>
    // quickSort 方法：对数组进行排序，改变原数组内的顺序（数组缓冲不能使用），要求元素必须支持比较操作（即，带有 Ordered 特质）
    println(e.mkString("(", ",", ")")) // output: (1,7,2,9)
    scala.util.Sorting.quickSort(e)
    println(e.mkString("(", ",", ")")) // output: (1,2,7,9)
    // mkString 方法：类似与 toString 方法，但是有区别
    // 对数组和数组缓冲而言 mkString 都是把集合内元素拼接成字符串
    // 对数组而言 toString 输出的是内存地址，对数组缓冲而言 toString 输出的是类型加上元素内容，报告了类型便于调试
    println(e.mkString(" ")) // output: 1 2 7 9
    println(e.toString) // output: [I@5cc7c2a6
    println(f.mkString(" ")) // output: 1 7 2 9
    println(f.toString) // output: ArrayBuffer(1, 7, 2, 9)

    // 多维数组
    // 1、创建多维数组（多维数组的类型类似于Array[Array[Double]]）
    // 创建规则多维数组，用 ofDim 函数
    val matrix = Array.ofDim[Double](3, 4)
    // 创建不规则多维数组
    val triangle = new Array[Array[Int]](10)
    for (i <- triangle.indices) {
      triangle(i) = new Array[Int](i + 1)
    }
    // 2.多维数组元素的取用
    matrix(1)(3) = 42

    // 与Java的互操作
    // 1、Java中允许给定类型的数组可以被转换成超类型的数组，例如String[]数组可以被传入一个以Object[]作为参数的方法。
    // 但是在Scala中这样做被认为是不安全的，所以应该采用如下的做法
    val j = Array("Mary", "a", "had", "lamb", "little")
    java.util.Arrays.binarySearch(a.asInstanceOf[Array[Object]], "beef")
    // 以上仅仅是个例子，在Scala中查找我们会这样做
    import scala.collection.Searching._
    val result = j.search("beef") // 在位置n处查找成功会返回Found(n)；未查找到但是应该被插入到位置n之前，结果则是InsertionPoint(n)
    // 2、当调用一个Java方法，其接受或者返回java.util.List，我们会引入scala.collection.JavaConversions中的方法
    // eg. java.util.ProcessBuilder类中有一个以List(String)为参数的构造器，在Scala中调用写法如下
    import scala.collection.JavaConversions.bufferAsJavaList
    val command = ArrayBuffer("ls", "-al", "/home/cay")
    val pb = new ProcessBuilder(command)
    // 反之，我们也可以让Java方法返回的List自动转换成一个Buffer
    import scala.collection.JavaConversions.asScalaBuffer
    val cmd: Buffer[String] = pb.command()
  }
}
