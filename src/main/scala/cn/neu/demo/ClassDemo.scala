package cn.neu.demo

/*
// 在Scala中，类并不声明为public。Scala源文件可以包含多个类，所有的这些类都具有公有可见性（public class）
class Counter {
  private var value = 0

  def increment() {
    value += 1
  }

  def current() = value

  // def current = value
}
*/

class A {

}

class B {

}

/* 2.1、
class Person {
  // Scala生成面向JVM的类，其中有一个私有的字段age以及相应的公有的getter和setter方法。
  // 因为我们没有将age声明为private，对私有字段而言，getter和setter方法都是私有的
  // Scala中，getter和setter分别叫做age和age_=方法（在反编译之后能看到）
  var age = 0
}
*/

/* 2.2、
class Person {
  // 如上述所说，反编译之后发现生成了privateAge和privateAge_=方法，但是是私有的
  // 这样实现的getter和setter的重写，但是字段名在这里不能和方法名一样(原因的话猜测是scala编译器要生成一个privateAge方法)
  private var privateAge = 0

  def age = privateAge

  def age_=(newValue: Int): Unit = {
    if (newValue > privateAge) privateAge = newValue
  }
}
*/

/* 3、
class Message {
  // 需要一个制度属性，有getter但是没有setter。如果属性值在构建对象之后就不再改变可以使用val字段
  // 使用val之后，Scala编译器会对应生成一个私有的final字段和一个getter方法，但是没有setter
  val timeStamp = java.time.Instant.now
}
*/

/* 4、
class Counter {
  private var value = 0

  def increment() {
    value += 1
  }

  // 可以访问另一个对象的私有字段，
  // 跟java和c++一样，方法可以访问该类的所有对象的私有字段
  // Scala允许我们定义更加严格的访问限制，通过private[this] var value = 0这种类似的形式，
  // 可以禁止相同类其他对象访问自己的字段，被称为对象私有
  def isLess(other: Counter): Boolean = value < other.value
}
*/

object ClassDemo {
  def main(args: Array[String]): Unit = {
    // 1、简单类和无参方法
    // val myCounter = new Counter // 或者new Counter()
    // myCounter.increment()
    // println(myCounter.current)
    // // 调用无参方法的时候，圆括号可以写可以不写
    // myCounter.current
    // myCounter.current()
    // 一般对于改值器方法使用括号，取值器方法我们不使用括号
    // 可以通过不带()的方式声明current来强制这种风格，def current =  value 定义中不带()
    // 这样类的使用者就必须使用 myCounter.current 这种不带括号的方式调用

    // 2、带getter和setter的属性
    // val fred = new Person
    // fred.age = 21
    // fred.age = 3
    // println(fred.age) // 无法重新使Person对象变得更年轻

  }

}
