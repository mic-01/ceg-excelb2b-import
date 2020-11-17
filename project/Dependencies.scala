import sbt._

object Dependencies {
  lazy val finalDependencies: Seq[ModuleID] = baseDependencies ++ sparkDependencies ++ excelDependencies

  private val versions = new {
    val sparkassembly = "2.4.6.0" //latest is here: https://github.agodadev.io/dse/spark-assembly/releases
    val sparkutils = "3.0.27" //latest is here: https://github.agodadev.io/it-data-frameworks/spark_frameworks/releases
    val hadooputils = "2.4.8" //latest is here: https://github.agodadev.io/it-data-frameworks/common_frameworks/releases
    val environment = "2.3.44" //latest is here: https://github.agodadev.io/it-data-frameworks/common_frameworks/releases
    val commonutils = "1.0.40" //latest is here: https://github.agodadev.io/it-data-frameworks/common_frameworks/releases
    val scalatest = "3.0.4"
    val scalamock = "3.6.0"
    val apachePoi = "3.17"
  }

  private val baseDependencies = Seq(
    "org.scalatest" %% "scalatest" % versions.scalatest % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % versions.scalamock % "test",
    "com.agoda.ml" %% "commonutils" % versions.commonutils,
    "com.agoda.ml" %% "commonutils" % versions.commonutils % "test" classifier "tests",
    "com.agoda.ml" %% "environment" % versions.environment
  )

  private val sparkDependencies = Seq(
    "com.agoda.ml" %% "hadooputils" % versions.hadooputils,
    "com.agoda.ml" %% "hadooputils" % versions.hadooputils % "test" classifier "tests",
    "com.agoda.ml" %% "sparkutils" % versions.sparkutils,
    "com.agoda.ml" %% "sparkutils" % versions.sparkutils % "test" classifier "tests",
    "org.apache.hadoop" % "hadoop-common" % "3.2.0" % "provided"
      excludeAll("com.fasterxml.jackson.core", "com.fasterxml.jackson.module", "com.fasterxml.jackson.jaxrs"),
    "org.apache.hadoop" % "hadoop-mapreduce-client-core" % "3.2.0" % "provided"
      excludeAll("com.fasterxml.jackson.core", "com.fasterxml.jackson.module", "com.fasterxml.jackson.jaxrs"),
    "com.agoda.ml" %% "sparkassembly" % versions.sparkassembly % "provided"
  )

  private val excelDependencies = Seq(
    "org.apache.poi" % "poi" % versions.apachePoi,
    "org.apache.poi" % "poi-ooxml" % versions.apachePoi
  )
}
