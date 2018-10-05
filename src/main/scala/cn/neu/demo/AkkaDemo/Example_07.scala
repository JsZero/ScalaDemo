package cn.neu.demo.AkkaDemo

import akka.actor.{ActorLogging, ActorRef}

/*
* 通过context隐式对象获取父Actor和子Actor的ActorRef
*/
object Example_07 extends App {

  import akka.actor.{Actor, ActorSystem, Props}

  // 父Actor
  class FirstActor extends Actor with ActorLogging {
    var child: ActorRef = _

    override def preStart(): Unit = {
      log.info("preStart() in FirstActor")
      child = context.actorOf(Props[MyActor], name = "myActor")
    }

    def receive = {
      case x => child ! x; log.info("received " + x)
    }
  }

  // 子Actor
  class MyActor extends Actor with ActorLogging {
    var parentActorRef: ActorRef = _

    override def preStart(): Unit = {
      //通过context.parent获取其父Actor的ActorRef
      parentActorRef = context.parent
    }

    def receive = {
      case "test" => log.info("received test"); parentActorRef ! "message from ParentActorRef"
      case _ => log.info("received unknown message");
    }

  }

  val system = ActorSystem("MyActorSystem")
  val systemLog = system.log

  //创建FirstActor对象
  val myactor = system.actorOf(Props[FirstActor], name = "firstActor")
  //获取ActorPath
  val myActorPath = system.child("firstActor")
  //通过system.actorSelection方法获取ActorRef
  val myActor1 = system.actorSelection(myActorPath)
  systemLog.info("准备向myactor发送消息")
  //向myActor1发送消息
  myActor1 ! "test"
  myActor1 ! 123
  Thread.sleep(5000)
  //关闭ActorSystem，停止程序的运行
  system.shutdown()
}
