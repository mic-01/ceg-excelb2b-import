import sbt.Keys._
import sbt._
import sbtrelease.ReleasePlugin.autoImport._

object Settings {
  final val Organization = "com.agoda.ceg.b2bimport"
  final val ScalaVersion = "2.12.2"

  final val Resolvers = Seq(
    "agoda-maven-hkg" at "https://repo-hkg.agodadev.io/agoda-maven"
  )

  final val baseSettings = Seq(
    organization := Organization,
    scalaVersion := ScalaVersion,
    resolvers ++= Resolvers,
    fork in Test := true,
    javaOptions in Test += "-Xmx4G",
    concurrentRestrictions in Global += Tags.limit(Tags.Test, 1),
    updateOptions := updateOptions.value.withCachedResolution(true)
  )

  final val publishingSettings = Seq(
    publishTo := Some("Artifactory Realm" at "http://repo.hkg.sdlc.agoda.local/artifactory/agoda-maven-ml"),
    credentials += Credentials(Path.userHome / ".ivy2" / "credentials")
  )

  final val releaseSettingsBase = publishingSettings ++ Seq(
    releaseTagName := s"${name.value}-v${version.value}",
    releaseTagComment := s"Releasing ${name.value} with version ${version.value}",
    releaseCommitMessage := s"Setting version of ${name.value} to ${version.value}"
  )

  final val releaseSettings = releaseSettingsBase ++ Seq(
    releaseVersionFile := baseDirectory.value / "version.sbt",
    releaseUseGlobalVersion := false
  )
}
