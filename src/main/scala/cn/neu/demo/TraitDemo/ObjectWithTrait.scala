package cn.neu.demo.TraitDemo

abstract class SavingAccount2 extends Account with Logger {
  def withdraw(amount: Double) = {
    if (amount > balance) log("Insufficient funds")
    else balance -= amount
  }
}

trait ConsoleLoggerTrait2 extends Logger {
  def log(msg: String): Unit = {
    println(msg)
  }
}

trait FileLogger extends Logger {
  def log(msg: String): Unit = {
    println("LOG:" + msg)
  }
}

object ObjectWithTrait {
  def main(args: Array[String]): Unit = {
    // 在构造对象的时候混入具体的实现
    val acct = new SavingAccount2 with ConsoleLoggerTrait2
    val acct2 = new SavingAccount2 with FileLogger
  }
}
