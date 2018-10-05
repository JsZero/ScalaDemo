package cn.neu.demo.hdfsDemo

import org.apache.commons.httpclient.methods.DeleteMethod
import org.apache.commons.httpclient.{HostConfiguration, HttpClient}

object HdfsRequestDemo {
  def main(args: Array[String]): Unit = {
    val url = "http://172.20.41.1:50070/webhdfs/v1/AIFCST/yum/test_js/2018-01-22?user.name=hadoop&op=DELETE"
    val httpClient = new HttpClient()
    val deleteMethod = new DeleteMethod()
    val result = httpClient.executeMethod(new HostConfiguration(), deleteMethod)
    println(result)
  }
}
