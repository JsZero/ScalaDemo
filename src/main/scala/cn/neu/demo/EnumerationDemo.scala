package cn.neu.demo

object TrafficLightColor extends Enumeration {
  type TrafficLightColor = Value
  val Red, Yellow, Green = Value
}

object EnumerationDemo {

  import TrafficLightColor._

  // 只有声明了第四行才能这样表示
  def doWhat(color: TrafficLightColor) = {
    if (color == Red) "stop"
    else if (color == Yellow) "hurry up"
    else "go"
  }

  def main(args: Array[String]): Unit = {
    for (c <- TrafficLightColor.values) println(s"${c.id}:$c")
    // 定位枚举类型
    TrafficLightColor(0) // 调用Enumeration.apply方法
    TrafficLightColor.withName("Red")
  }
}
