ThisBuild / scalaVersion := "2.13.2"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.github.rzeigler"
ThisBuild / organizationName := "rzeigler"

ThisBuild / scalacOptions += "-Xlog-implicits"

val compilerPlugins = Seq(
  compilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3"),
  compilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
  compilerPlugin(
    "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
  )
)

lazy val root = (project in file("."))
  .aggregate(core, doobie)

lazy val core = (project in file("core"))
  .settings(
    libraryDependencies += "org.typelevel" %% "cats-core" % "2.1.1",
    libraryDependencies += "com.chuusai" %% "shapeless" % "2.3.3",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % "test"
  )

lazy val doobie = (project in file("doobie"))

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
