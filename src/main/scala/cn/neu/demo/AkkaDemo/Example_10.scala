package cn.neu.demo.AkkaDemo

import akka.actor.{ActorLogging, ActorRef}

/*
*停止Actor:ActorSystem.shutdown方法
*/
object Example_10 extends App {

  import akka.actor.{Actor, ActorSystem, Props}

  class FirstActor extends Actor with ActorLogging {

    var child: ActorRef = context.actorOf(Props[MyActor], name = "myActor")

    def receive = {
      //向MyActor发送消息
      case x => child ! x; log.info("received " + x)
    }

    override def postStop(): Unit = {
      log.info("postStop In FirstActor")
    }
  }

  class MyActor extends Actor with ActorLogging {
    def receive = {
      case "test" => log.info("received test");
      case _ => log.info("received unknown message");
    }

    override def postStop(): Unit = {
      log.info("postStop In MyActor")
    }
  }

  val system = ActorSystem("MyActorSystem")
  val systemLog = system.log

  //创建FirstActor对象
  val firstactor = system.actorOf(Props[FirstActor], name = "firstActor")

  systemLog.info("准备向firstactor发送消息")
  //向firstactor发送消息
  firstactor ! "test"
  firstactor ! 123
  //关闭ActorSystem，停止所有Acotr运行
  system.shutdown()

}
