name := "jackson-scala-csv"

version := "0.1"

scalaVersion := "3.2.2"

idePackagePrefix := Some("com.github.pjfanning.jackson.csv")

//resolvers += Resolver.sonatypeRepo("snapshots")

val jacksonVersion = "2.15.1"

libraryDependencies ++= Seq(
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion,
  "com.fasterxml.jackson.dataformat" % "jackson-dataformat-csv" % jacksonVersion,
  "commons-io" % "commons-io" % "2.11.0"
)
