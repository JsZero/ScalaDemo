package cn.neu.demo.AkkaDemo

import akka.actor.ActorLogging

/*
 *创建Actor,调用context.actorOf方法
 */
object Example_04 extends App {

  import akka.actor.{Actor, ActorSystem, Props}

  class FirstActor extends Actor with ActorLogging {
    //通过context.actorOf方法创建Actor
    val child = context.actorOf(Props[MyActor], name = "myChild")

    def receive = {
      case x => child ! x; log.info("received " + x)
    }

  }


  class MyActor extends Actor with ActorLogging {
    def receive = {
      case "test" => log.info("received test")
      case _ => log.info("received unknown message")
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
