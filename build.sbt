import Settings._
import Dependencies._

val c = 1

val assemblySettings = Seq(
  assemblyJarName in assembly := "assembly.jar"
)

lazy val b2bimport = project
  .settings(baseSettings: _*)
  .enablePlugins(DeployPlugin)
  .settings(assemblySettings: _*)
  .settings(
    libraryDependencies ++= finalDependencies,
    name := "b2bimport"
  )