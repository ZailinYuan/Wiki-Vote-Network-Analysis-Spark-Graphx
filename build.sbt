name := "SocialNetwork"

version := "0.1"

scalaVersion := "2.11.8"

val sparkVersion = "2.4.1"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.1"
libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.4.1" % "runtime"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.1" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-hive" % "2.4.1" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-graphx" % "2.4.1"