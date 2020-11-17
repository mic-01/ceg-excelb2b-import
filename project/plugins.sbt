logLevel := Level.Warn

resolvers ++= Seq(
  "agoda-maven-hkg" at "https://repo-hkg.agodadev.io/agoda-maven",
  "cloudera-repo-releases" at "https://repository.cloudera.com/artifactory/repo"
)


addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.7")

addSbtPlugin("com.agoda.ml" % "sparkdeployplugin" % "2.1.64") //latest is here: https://github.agodadev.io/dse/sparkDeployPlugin/releases
