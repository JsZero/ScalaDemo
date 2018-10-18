name := "ScalaDemo"

version := "0.1"

scalaVersion := "2.11.11"

scalaVersion in ThisBuild := "2.11.1"

lazy val commonSettings = Seq(
  organization := "com.neu",
  version := "0.1.0-SNAPSHOT"
)

lazy val app = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "fat-jar-test"
  ).enablePlugins()

assemblyMergeStrategy in assembly := {
  case PathList("javax", "servlet", xs@_*) => MergeStrategy.last
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case n if n.startsWith("reference.conf") => MergeStrategy.concat
  case x => MergeStrategy.first
}

//unmanagedJars in Compile += file("lib/postgresql-42.2.5.jar")

libraryDependencies += "org.scalaj" % "scalaj-http_2.11" % "2.3.0"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.16"
libraryDependencies += "org.apache.hadoop" % "hadoop-hdfs" % "2.7.3"
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.7.3"
libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.7.3"
libraryDependencies += "commons-httpclient" % "commons-httpclient" % "3.1"
// https://mvnrepository.com/artifact/org.apache.directory.studio/org.apache.commons.collections
libraryDependencies += "org.apache.directory.studio" % "org.apache.commons.collections" % "3.2.1"
// https://mvnrepository.com/artifact/org.apache.spark/spark-core--
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.3.1"
// https://mvnrepository.com/artifact/org.apache.spark/spark-sql
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.1"
// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-common
libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.7.3"
