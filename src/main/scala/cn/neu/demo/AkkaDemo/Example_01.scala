package cn.neu.demo.AkkaDemo


object Example_01 extends App {

  import akka.actor.{Actor, ActorSystem, Props}
  import akka.event.Logging

  class MyActor extends Actor {
    val log = Logging(context.system, this)

    def receive = {
      case "test" => log.error("received test")
      case _ => log.info("received unknown message")
    }
  }

  //创建ActorSystem对象
  val system = ActorSystem("MyActorSystem")
  //返回ActorSystem的LoggingAdpater
  val systemLog = system.log
  //创建MyActor,指定actor名称为myactor
  val myactor = system.actorOf(Props[MyActor], name = "myactor")

  systemLog.info("准备向myactor发送消息")
  //向myactor发送消息
  myactor ! "test"
  myactor ! 123

  //关闭ActorSystem，停止程序的运行
  system.shutdown()

}
