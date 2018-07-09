package cn.neu.demo.practise.chapter8.first

class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
  override def deposit(amount: Double): Double = super.deposit(amount - 1)

  override def withdraw(amount: Double): Double = super.withdraw(amount + 1)
}

object Test {
  def main(args: Array[String]): Unit = {
    val ba = new BankAccount(1000)
    println(f"余额为${ba.currentBalance}")
    ba.deposit(50)
    println(f"余额为${ba.currentBalance}")
    ba.withdraw(30)
    println(f"余额为${ba.currentBalance}")

    val ca = new CheckingAccount(1000)
    println(f"余额为${ca.currentBalance}")
    ca.deposit(500)
    println(f"余额为${ca.currentBalance}")
    ca.withdraw(300)
    println(f"余额为${ca.currentBalance}")
  }
}
