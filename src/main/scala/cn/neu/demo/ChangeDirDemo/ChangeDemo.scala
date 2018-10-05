package cn.neu.demo.ChangeDirDemo

import java.io.File

object ChangeDemo {
  def main(args: Array[String]): Unit = {
    val dirPath = "C:\\Users\\imsha\\Desktop\\Yum预估二期\\xhl的py脚本\\yum_1\\data\\hdfs_data\\store"
    val dir = new File(dirPath)
    val childDir = dir.listFiles.filter(x => x.isDirectory)
    for (child <- childDir) {
      val path = child.getAbsolutePath + "\\input_data"
      val finalDir = new File(path)
      finalDir.listFiles.filter(!_.getName.startsWith("\\.")).foreach(x => {
        val tmp =x.getName
        val p = x.getAbsolutePath.take(85) + x.getName.split("\\.")(0)
        if (x.getName.startsWith("dim")) {
          val d = new File(p + "\\20180819")
          if(d.exists())
            d.delete()
          d.mkdirs()
          println("创建"+d+"目录")
          x.renameTo(new File(p + "\\20180819\\"+tmp))
        } else {
          val d = new File(p)
          if(d.exists())
            d.delete()
          d.mkdirs()
          println("创建"+d+"目录")
          x.renameTo(new File(p+"\\"+tmp))
        }
      })
    }
  }
}
