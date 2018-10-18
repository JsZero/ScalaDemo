package cn.neu.demo.SparkDemo

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkDemo {

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder().appName("SparkDemo").getOrCreate()
    val hdfsSuffixPath = "hdfs://172.20.41.1:54310/AIFCST/yum/jstest/"
    //    val hdfsSuffixPath = "D:/AIFCST/yum/jstest/"
    val path = new Path(hdfsSuffixPath)
    val hdfsFileSystem = org.apache.hadoop.fs.FileSystem.get(new java.net.URI(hdfsSuffixPath), new org.apache.hadoop.conf.Configuration())

    //list files
    val paths = listTarFiles(path, hdfsFileSystem)
    println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
    println("paths.length: " + paths.length)
    println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")

    for (path <- paths) {
      var source: DataFrame = null
      if (path.toString.endsWith("csv")) {
        source = sparkSession.read.option("header", "true").csv(path.toString)
      } else if (path.toString.endsWith("pkl")) {
        source = sparkSession.read.textFile(path.toString).toDF
      }
      println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
      println("source: " + source.count)
      println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
      val target = source.distinct()
      println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
      println("target: " + target.count)
      println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
      if (hdfsFileSystem.exists(path))
        hdfsFileSystem.delete(path, true)
      if (path.toString.endsWith("csv")) {
      } else if (path.toString.endsWith("pkl")) {
        target.write.text(path.toString)
      }
    }

  }

  def listTarFiles(hdfsPath: Path, hdfsFileSystem: FileSystem): List[Path] = {
    if (hdfsFileSystem != null) {
      val fileStatuses = hdfsFileSystem.listStatus(hdfsPath)
      var li = List[Path]()
      for (file <- fileStatuses) {
        //        println("filename: " + file.getPath)
        //        println("parentname: " + file.getPath.getParent.getName)
        if ((file.getPath.getName.endsWith("csv") || file.getPath.getName.endsWith("pkl")) && !(file.getPath.getParent.getName.endsWith("csv") || file.getPath.getParent.getName.endsWith("pkl"))) {
          li = file.getPath +: li
          println(file.getPath.toString)
        } else if (file.isDirectory && !(file.getPath.getParent.getName.endsWith("csv") || file.getPath.getParent.getName.endsWith("pkl"))) {
          li = li ::: listTarFiles(file.getPath, hdfsFileSystem)
        }
      }
      li
    } else {
      Nil
    }
  }

}
