package cn.neu.demo.SparkDemo

object Demo {
  def main(args: Array[String]): Unit = {
    val hdfsSuffixPath = "hdfs://172.20.41.1:54310/AIFCST/yum/jstest/"
    val hdfsFileSystem = org.apache.hadoop.fs.FileSystem.get(new java.net.URI(hdfsSuffixPath), new org.apache.hadoop.conf.Configuration())
    println("=======================================================")
    println()
    println("=======================================================")
  }
}
