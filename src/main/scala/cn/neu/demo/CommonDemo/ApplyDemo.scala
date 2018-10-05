package cn.neu.demo.CommonDemo

class AccountAD private(val id: Int, initialBalance: Double) {
  private var balance = initialBalance

  def deposit(amount: Double) {
    balance += amount
  }
}

object AccountAD {
  private var lastNumber = 0

  def apply(initialBalance: Double) = new AccountAD(newUniqueNumber(), initialBalance)

  private def newUniqueNumber() = {
    lastNumber += 1
    lastNumber
  }
}

object ApplyDemo {
  def main(args: Array[String]): Unit = {
    val acct = AccountAD(1000.0) // apply方法会调用相应的Class的构造器，省去了new关键字，简化了写法
  }
}
