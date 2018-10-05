package cn.neu.demo.AkkaDemo

import akka.actor.ActorLogging

/*
*Actor API:unhandled方法的使用
*/
object Example_06 extends App {

  import akka.actor.{Actor, ActorSystem, Props}


  class FirstActor extends Actor with ActorLogging {
    def receive = {
      //向MyActor发送消息
      case "test" => log.info("received test")
    }

    //重写unhandled方法
    override def unhandled(message: Any): Unit = {
      log.info("unhandled message is {}", message)
    }
  }


  val system = ActorSystem("MyActorSystem")
  val systemLog = system.log

  //创建FirstActor对象
  val myactor = system.actorOf(Props[FirstActor], name = "firstActor")

  systemLog.info("准备向myactor发送消息")
  //向myactor发送消息
  myactor ! "test"
  myactor ! 123
  Thread.sleep(5000)
  //关闭ActorSystem，停止程序的运行
  system.shutdown()
}
