name := "bug-demo"

scalaVersion  := "2.11.6"

libraryDependencies ++= {
  Seq(
    "com.typesafe.akka" % "akka-http-core-experimental_2.11" % "1.0-RC4",
    "com.typesafe.akka" % "akka-http-experimental_2.11" % "1.0-RC4"
  )
}
