package cn.neu.demo.ConfDemo

import java.io.{File, FileInputStream}
import java.util.Properties

import com.typesafe.config.ConfigFactory

import scala.util.Try

object Conf {
  var conf = ConfigFactory.load()

  var lock: Boolean = false
  private var prop = new Properties()
  prop.load(new FileInputStream(new File(conf.getString("config_path"))))

  def init(): Unit = {
    lock = true
    prop.load(new FileInputStream(new File(conf.getString("config_path"))))
    lock = false
    println("+++++++++++++++++++++++++++++++++++++++++++++++++++")
    println(conf.getString("config_path"))
    println("+++++++++++++++++++++++++++++++++++++++++++++++++++")
  }

  /**
    * 直接从配置文件中读取信息
    *
    * @param key
    * @return
    */

  def getString(key: String): Option[String] = {
    while (lock) {}
    if (prop.getProperty(key) == null)
      None
    else
      Some(prop.getProperty(key).replaceAll("\"", ""))

  }

  def getInt(key: String): Option[Int] = {
    while (lock) {}
    var varue = Try(prop.getProperty(key).toInt)
    varue.toOption
  }

  def getDouble(key: String): Option[Double] = {
    while (lock) {}
    var varue = Try(prop.getProperty(key).toDouble)
    varue.toOption
  }

  def getLong(key: String): Option[Long] = {
    while (lock) {}
    var varue = Try(prop.getProperty(key).toLong)
    varue.toOption
  }

  def main(args: Array[String]): Unit = {
    Conf.init()
  }
}
