name := """feedback-survey"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "com.h2database" % "h2" % "1.4.197",
  "org.postgresql" % "postgresql" % "9.4-1200-jdbc4"
)
