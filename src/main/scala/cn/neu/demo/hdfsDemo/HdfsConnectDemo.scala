package cn.neu.demo.hdfsDemo

import java.net.URI

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.hdfs.DistributedFileSystem

object HdfsConnectDemo {
  def main(args: Array[String]): Unit = {
    val conf: Configuration = new Configuration()
    conf.set("fs.hdfs.impl", classOf[DistributedFileSystem].getName)
    val uri = "hdfs://172.20.41.11:50070"
    val fs = FileSystem.get(URI.create(uri), conf, "hadoop")
  }
}
