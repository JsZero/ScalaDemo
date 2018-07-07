package cn.neu.demo.InheritDemo

import java.nio.charset.Charset
import java.nio.file.{Files, Path}


/**
  * 1、扩展类
  * extends 继承父类
  * final 修饰的类将不能被扩展
  * （但是将单个字段或者方法声明为final可以确保它们不被重写，而不是像Java中一样是不可变的）
  */
/*
class Employee extends Person {
  var salary = 0.0

  // 2、重写方法
  // 重写非抽象方法必须使用override修饰符，super调用超类的方法
  override def toString: String = s"${getClass.getName}[name=$name]"
}
*/
class Person(var name: String, var age: Int) {
//  def this() {
//    this("s","s")
//  }
}

// 5、超类的构造
// 子类的辅助构造器最终都会调用主构造器，只有主构造器可以调用超类的构造器
class Employee(name: String, age: Int, val salary: Double) extends Person(name, age)

// Scala类扩展Java类
class PathWriter(p: Path, cs: Charset) extends java.io.PrintWriter(Files.newBufferedWriter(p, cs))

object Employee {
  def main(args: Array[String]): Unit = {
    val p = new Person("",2)
    // 3、类型检查与类型转换
    if (p.isInstanceOf[Employee]) { // p是Employee或其子类对象，返回true，否则返回false
      val s = p.asInstanceOf[Employee] // p是Employee或其子类对象，返回Employee对象，否则抛出异常
    }
    // 测试p指向的是一个Employee对象而不是其子类对象
    println((p.getClass == classOf[Employee]))
    // 但是还是不这么写比较好，为什么吗，我们是函数式编程！要有逼格！
    // 要用这种模式匹配
    p match {
      case s: Employee => println("hello")
      case _ => println("Hello")
    }
    // 4、受保护的字段和方法
    // protected成员对于类所属的包而言是不可见的
    // 还有一个protected[this]和private[this]类似，但是区别书上没讲


  }
}
