package cn.neu.demo.practise.chapter8.first

class BankAccount(initialBalance: Double) {
  private var balance = initialBalance

  def currentBalance = balance

  def deposit(amount: Double) = {
    balance += amount; balance
  }

  def withdraw(amount: Double) = {
    balance -= amount; balance
  }
}
