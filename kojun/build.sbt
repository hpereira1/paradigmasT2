val scala3Version = "3.3.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "kojun",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,
    scalacOptions += "-explain",
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
