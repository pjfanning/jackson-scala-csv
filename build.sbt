name := "jackson-scala-csv"

version := "0.1"

scalaVersion := "2.13.6"

idePackagePrefix := Some("com.github.pjfanning.jackson.csv")

resolvers += Resolver.sonatypeRepo("snapshots")

val jacksonVersion = "2.13.0-rc1"

libraryDependencies ++= Seq(
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion,
  "com.fasterxml.jackson.dataformat" % "jackson-dataformat-csv" % jacksonVersion,
  "com.github.pjfanning" %% "jackson-module-enumeratum" % "2.12.1",
  "com.beachape" %% "enumeratum-play" % "1.7.0",
  "commons-io" % "commons-io" % "2.11.0"
)
