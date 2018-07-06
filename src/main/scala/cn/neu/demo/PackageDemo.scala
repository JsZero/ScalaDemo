package cn.neu.demo

package com {
  package horstmamnn {
    package impatient {

      class Employee {}

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
// 当文件中所有的代码属于同一个包的时候，这是比较好的做法
class PackageDemo {
  def main(args: Array[String]): Unit = {
    // 1、包
    // 在Scala中
    // 1.同一个包可以定义在多个文件中
    // 2.源文件目录和包之间并没有强制的关联关系（也可以说成是，一个文件可以包含多个包的内容）
    //   ，不必把文件写在相应的目录下，但是推荐这么做
  }

}
