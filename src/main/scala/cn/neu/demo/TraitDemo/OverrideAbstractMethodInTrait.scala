package cn.neu.demo.TraitDemo

trait A {
  def log(msg: String)

  def logP(msg: String)
}

trait B extends A {
  abstract override def log(msg: String): Unit = { // 调用父类中的抽象方法log（就是super.log()），需要在子类中重写方法并显式声明为abstract override
    super.log(s"${java.time.Instant.now()} $msg")
  }
  abstract override def logP(msg: String): Unit = {
    super.log(msg)
  }
}

trait C extends A {
  override def log(msg: String): Unit = {
    println(f"[LOG_INFO]:${msg}")
  }
}

object OverrideAbstractMethodInTrait {
  def main(args: Array[String]): Unit = {

  }
}

/*
 编译之后发现特质编译之后会生成两个文件，分别是抽象类（X$class）和接口(X)
 类中存储的是静态方法public static，接口中存储的是抽象方法public abstract
*/

