package cn.neu.demo

// 在Scala中，类并不声明为public。Scala源文件可以包含多个类，所有的这些类都具有公有可见性（public class）
class Counter {
  private var value = 0

  def increment() {
    value += 1
  }

  def current() = value

  // def current = value
}

class A {

}

class B {

}


object ClassDemo {
  def main(args: Array[String]): Unit = {
    // 1、简单类和无参方法
    val myCounter = new Counter // 或者new Counter()
    myCounter.increment()
    println(myCounter.current)
    // 调用无参方法的时候，圆括号可以写可以不写
    myCounter.current
    myCounter.current()
    // 一般对于改值器方法使用括号，取值器方法我们不使用括号
    // 可以通过不带()的方式声明current来强制这种风格，def current =  value 定义中不带()
    // 这样类的使用者就必须使用 myCounter.current 这种不带括号的方式调用
  }
}
