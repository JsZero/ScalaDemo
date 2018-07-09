package cn.neu.demo.TraitDemo

trait Logger {
  def log(msg: String) // 不必声明为抽象的，默认是public abstract
}

class ConsoleLogger extends Logger with Cloneable with Serializable { // extends，而不是implements,用with添加额外的特质
  def log(msg: String): Unit = { // 在重写特质的抽象方法的时候无须给出override关键字
    println(msg)
  }
}

trait ConsoleLoggerTrait {
  def log(msg: String) { // 特质中可以存在具体的方法
    println(msg)
  }
}

class Account { // 伴生类
  protected var balance = 0.0

  def deposit(amount: Double) {
    balance += amount
  }
}

class SavingAccount extends Account with ConsoleLoggerTrait { // 我们称ConsoleLoggerTrait的特质被混入了SavingAccount
  def withdraw(amount: Double): Unit = {
    if (amount > balance) log("Insufficient funds")
    else balance -= amount
  }
}
