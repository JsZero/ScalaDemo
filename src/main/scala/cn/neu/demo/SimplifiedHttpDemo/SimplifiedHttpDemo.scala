package cn.neu.demo.SimplifiedHttpDemo

import scalaj.http.{Http, HttpResponse}

import scala.io.Source

object SimplifiedHttpDemo {
  def main(args: Array[String]): Unit = {
    val source = Source.fromFile("C:\\Users\\imsha\\Desktop\\Yum预估二期\\database_output_20181011.csv")
    val lineIter = source.getLines()
    while (lineIter.hasNext) {
      val strs = lineIter.next().split(",")
      println("inserting (" + strs(0) + "：" + strs(1) + "：" + strs(2) + "：" + strs(3) + "：" + strs(4) + "：" + strs(5) + ": " + strs(6) + ") ... ...")
      val response: HttpResponse[String] = Http("http://172.20.41.8:8081/dataCodeName/create").params(Seq(
        ("data_code", strs(0)),
        ("data_name", strs(1)),
        ("update_cycle", strs(2)),
        ("need_cycle", strs(3)),
        ("date_column_name", strs(4)),
        ("relative_path", strs(5)),
        ("data_type", strs(6))
      )).asString
      println("done with response: " + response.body)
    }
    source.close()
  }
}
