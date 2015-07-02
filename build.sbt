import sbt.Keys._
import sbt.Keys.libraryDependencies

name := "scala-macro-test"

version := "0.1.0"

scalaVersion in ThisBuild := "2.11.4"

lazy val macros = project in file("macros")

libraryDependencies in ThisBuild ++= Seq(
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  compilerPlugin("org.scalamacros" % "paradise" % "2.0.1" cross CrossVersion.full)
)

lazy val root = project in file(".") dependsOn macros

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }
