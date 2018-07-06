package cn.neu.demo

package com {
  package horstmamnn {
    package impatient {

      class Employee {}

      g

    }

  }

}

package com {
  package horstmann {
    /*
    // 如果定义这个collection，31行的collection会报错，因为Scala编译器会在这个collection下寻找mutable成员，但是并不能找到
    // java中不会出现这个问题，因为java包名是绝对的，Scala中是相对的，解决方案在31行
    package collection {

    }
    */

    package impatient {

      import cn.neu.demo.com.horstmamnn.impatient.Employee

      class Manager {
        // 2、作用域规则
        val subordinates = new collection.mutable.ArrayBuffer[Employee]
        // 为解决地18行提及的问题，解决方案是使用绝对包名，以_root_开始，使用如下代码来解决
        // val subordinates = new _root_.scala.collection.mutable.ArrayBuffer[Employee]
      }

    }

  }

}

// 3、串联式包语句
// 限定了可见的成员，com.horstmann.impatient包不再能够以collection访问到了
package com.horstmann.impatient {
  // com和com.horstmann的成员在这里是不可见的
  package people {

    class Person {}

  }

}

// 4、文件顶部标记法
// 除了嵌套的标记方法之外，我们可以在文件顶部使用package语句，不带"{ }"
// package com.horstmann.impatient
// package people
// ... ...
// class Person
// ...
// 相当于
// package com.horstmann.impatient{
//   package people{
//     class Person
//     ...
//   }
// }
// 当文件中所有的代码属于同一个包的时候，这是比较好的做法，
// people 包内的内容可以直接引用 impatient 包的内容

// 5、包对象
// 包可以包含类，对象和特质，但是不能包含函数和变量的定义
// 为了解决这个问题出现了包对象
package object people {
  val defaultName = "John Q.Public"
}
package people {

  class Person {
    var name = defaultName // 从包对象拿到的变量
    // 在本包下访问所以不需要加限定词
  }

}
// 6、包可见性
// 在 Java 中可见性中不写修饰符的话默认是包含该类的包下可见
// 在 Scala 中需要使用 private[包名] 指定在本包或者上层包可见

// 7、包的引入
// 类似Java中的引入，但是Scala中引入的通配符是"_"表示所有
// import java.awt._

// 8、Scala中在任何地方都可以引入包

// 9、重命名和隐藏方法
// 选取器引入多个成员
import java.awt.{Color, Font}

import scala.collection.mutable
// 选取器选取成员并重命名
// 以下方式我们可以使用JavaHashMap访问Java的hashmap，而用HashMap访问Scala中的HashMap
// 选取器在进行选取的时候应该把"_"通配符放在最后，否则报错
import java.util.{HashMap=>JavaHashMap}
import scala.collection.mutable._
// =>_ 隐藏某个成员而不是重命名，用来解决冲突问题
import java.util.{HashMap=>_,_}

class PackageDemo {
  def main(args: Array[String]): Unit = {
    // 1、包
    // 在Scala中
    // 1.同一个包可以定义在多个文件中
    // 2.源文件目录和包之间并没有强制的关联关系（也可以说成是，一个文件可以包含多个包的内容）
    //   ，不必把文件写在相应的目录下，但是推荐这么做
  }

}
