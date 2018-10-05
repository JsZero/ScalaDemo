package cn.neu.demo.AkkaDemo

import akka.actor.ActorLogging

/*
 *创建Actor
 */
object Example_03 extends App {

  import akka.actor.{Actor, ActorSystem, Props}

  class MyActor extends Actor with ActorLogging {
    def receive = {
      case "test" => log.info("received test")
      case _ => log.info("received unknown message")
    }
  }

  val system = ActorSystem("MyActorSystem")
  val systemLog = system.log

  //下列两行代码编译可以通过，但运行时出抛出异常
  val myActor = new MyActor
  val myactor = system.actorOf(Props(myActor), name = "myactor")

  systemLog.info("准备向myactor发送消息")
  //向myactor发送消息
  myactor ! "test"
  myactor ! 123

  //关闭ActorSystem，停止程序的运行
  system.shutdown()
}
