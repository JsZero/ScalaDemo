package cn.neu.demo.InheritDemo

/**
  * 在Scala中抽象方法不需要声明为abstract只要省略方法体即可，如果某个类存在抽象方法，那么该类必须被声明为abstract
  * 在Java中抽象方法需要声明为abstract，如果某个类存在抽象方法，那么该类必须被声明为abstract
  */
abstract class A(val name: String) {
  // 抽象方法
  def id: Int // 抽象方法的返回类型如果不写默认是Unit
}

/*
public abstract class A
{
    public String name(){
        return name;
    }
    public abstract int id();
    public A(String name){
        this.name = name;
        super();
    }
    private final String name;
}
*/
class ASubclass(name: String) extends A(name) {
  override def id: Int = {
    0
  }
}

abstract class B {
  val id: Int // 没有初始化，这是一个带有抽象的getter方法的抽象字段
  var name: String // 另一个抽象字段，带有抽象的getter和setter方法
}

/* 编译生成的抽象类
public abstract class B{
    public abstract int id();
    public abstract String name();
    public abstract void name_$eq(String s);
    public B(){
    }
}
*/
class BSubclass(val id: Int) extends B {
  //  var name: String
  var name: String = ""
}

object AbstractClass {
  def main(args: Array[String]): Unit = {

  }
}
