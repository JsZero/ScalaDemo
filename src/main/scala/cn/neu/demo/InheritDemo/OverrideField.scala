package cn.neu.demo.InheritDemo

class Persons(val name: String) {
  override def toString: String = s"${getClass.getName}[name=$name]"
}

class SecretAgent(codeName: String) extends Persons(codeName) {
  override val name: String = "sercet"

  override def toString: String = "sercet"
}

object OverrideField {
  def main(args: Array[String]): Unit = {

  }
}

