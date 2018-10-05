package cn.neu.demo.CommonDemo

object FunctionDemo {
  def main(args: Array[String]): Unit = {
    // 在Scala中存在函数（function）和方法（method），函数和方法是不同的存在，方法可以说是类的一部分，而函数是一个对象
    // 详细的区别如下所示：
    // 1. 方法不能作为单独的表达式而存在，而函数是可以的
    // 2. 函数必须要有参数列表，而方法可以没有参数列表
    // 3. 方法名是方法调用，而函数名只是代表函数对象本身
    // 4. 在需要函数的地方，如果传递一个方法，会自动进行ETA展开（把方法转换为函数）
    // 5.传名参数本质上是个方法
    // 详见 https://blog.csdn.net/u012302488/article/details/50543661

    // 一、函数的定义：必须拥有函数的名称、参数列表和函数体
    //               可以不写返回类型，编译器会根据等号右面的表达式加以推断（递归函数除外）
    def fac(n: Int): Int = {
      var r = 1
      for (i <- 1 to n) r = r * i
      r
    }

    // 二、默认参数和带名参数：可以指定默认参数
    def decorate(str: String, left: String = "[", right: String = "]") = left + str + right
    // 全部使用默认参数
    println(decorate("Hello")) // output:[Hello]
    // 使用部分默认参数
    println(decorate("Hello", ">>>[")) // output:>>>[Hello]
    // 全部使用带名参数（可交换顺序）
    println(decorate(left = "<<<", str = "Hello", right = ">>>")) // output:<<<Hello>>>
    // 使用部分带名参数
    println(decorate("Hello", right = "]<<<")) // output:[Hello]<<<

    // 三、变长参数
    def sum(args: Int*) = {
      var res = 0
      for (arg <- args) res += arg
      res
    }

    // 函数在这里得到的式一个Seq的参数
    println(sum(1, 4, 9, 16, 25)) // output:55

    // 但是如果我们直接传递一个值的序列作为参数，则会报错
    // val s = sum(1 to 5)
    //需要追加一个":_*"，这会将其转换成一个参数序列
    val s = sum(1 to 5: _*)
    println(s) // output:15
    // 多个这样也会编译出错
    // sum(1 to 5: _*,3 to 6:_*)

    //在递归中我们可能会用到上述的规则，如
    def recursiveSum(args: Int*): Int = {
      if (args.isEmpty) 0
      else args.head + recursiveSum(args.tail: _*)
    }

    // 四、过程
    // 在Scala中，不返回任何值的函数被称为过程（procedure），调用过程是为了他所产生的副作用
    // 写法1.不带等号（快学Scala中的写法）
    def box1(s: String) {
      val border = "-" * (s.length + 2)
      print(f"$border%n|$s|%n$border%n")
    }

    // 写法2.其实就是让编译器推测出返回结果
    def box2(s: String) = {
      val border = "-" * (s.length + 2)
      print(f"$border%n|$s|%n$border%n")
    }

    // 写法3.标注出返回值Unit
    def box3(s: String): Unit = {
      val border = "-" * (s.length + 2)
      print(f"$border%n|$s|%n$border%n")
    }
    //以上三种写法，写的时候还是按照第三个来吧，第一种在写非过程函数的时候忘记写等号，容易造成错误
    // ，第二种万一函数最后一表达式有值的话，就不是过程了
  }
}
