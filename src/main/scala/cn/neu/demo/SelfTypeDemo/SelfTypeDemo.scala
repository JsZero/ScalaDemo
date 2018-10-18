package cn.neu.demo.SelfTypeDemo

object SelfTypeDemo extends App {

  trait User {
    def username: String
  }

  trait Tweeter {
    this: User => // reassign this
    def tweet(tweetText: String) = println(s"$username: $tweetText")
  }

  class VerifiedTweeter(val usernameP : String) extends User with Tweeter { // We mixin User because Tweeter required it
    def username = s"real $usernameP"
  }

  val realBeyoncé = new VerifiedTweeter("Beyoncé")
  realBeyoncé.tweet("Just spilled my glass of lemonade") // prints "real Beyoncé: Just spilled my glass of lemonade"

}
