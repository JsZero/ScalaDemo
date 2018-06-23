package cn.neu.demo

object FunctionDemo {
  def main(args: Array[String]): Unit = {
    // 在Scala中存在函数（function）和方法（method），函数和方法是不同的存在，区别如下所示：
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

    //二、默认参数和带名参数
    def decorate(str: String, left: String = "[", right: String = "]") = left + str + right

    print(decorate("Hello"))

  }
}
