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
  // 使用Setter方法的时候，我们使用p.age=9，实际上是p.age_=(9)这样调用
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

/* 3、只带getter属性
class Message {
  // 需要一个制度属性，有getter但是没有setter。如果属性值在构建对象之后就不再改变可以使用val字段
  // 使用val之后，Scala编译器会对应生成一个私有的final字段和一个getter方法，但是没有setter
  val timeStamp = java.time.Instant.now
}
*/

/* 4、对象私有字段
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

/* 5、Bean属性
// 导包加注解生成遵循java中规则的形如getName和setName的getter和setter
import scala.beans.BeanProperty

class Person {
  // 生成了四个方法：
  // name:String
  // name_=(newValue:String):Unit
  // getName():String
  // setName(newValue:String):Unit
  @BeanProperty var name: String = _
}
*/

/* 6、辅助构造器
// 构建辅助构造器的原则
// 1.辅助构造器的名字是this（比Java好因为改类名的时候不用改变了）
// 2.每一个辅助构造器都必须是对先前已定义的其他辅助构造器或主构造器的调用开始
class Person {
  private var name = ""
  private var age = 0

  def this(name: String) { // 辅助构造器1
    this() // 调用主构造器
    this.name = name
  }

  def this(name: String, age: Int) { // 辅助构造器2
    this(name) // 调用前一个辅助构造器
    this.age = age
  }
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

    // 5、Bean属性
    // val p = new Person
    // println(p.name)

    // 6、辅助构造器
    // val p1 = new Person // 调用主构造器
    // val p2 = new Person("Fred") // 调用辅助构造器1
    // val p3 = new Person("Fred", 23) // 调用辅助构造器2

    // 7、主构造器
    // P73页，书上讲的比较全，概括下来要点有这几个
    // 1.主构造器直接放在类名之后
    // 2.主构造器会执行类定义中的所有语句
    // 3.参数可以被任何修饰符修饰（var/val,@BeanProperty val/var,private val/var,private[this] var/val,private[T] var/val ）
    // 4.参数不带val或者var，如果被方法调用过会被自动升格为对象私有（private[this] val）的字段，
    // 否则将仅仅作为一个简单的参数
    // 5.在参数括号前面加private可以使主构造器私有化，强制使用主构造器创建对象

  }

}
