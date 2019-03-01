name := "LicenseKeyGenerator"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "commons-codec" % "commons-codec" % "1.12",

  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)