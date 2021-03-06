organization := "com.kifi"

name := "franz"

crossScalaVersions := Seq("2.11.8", "2.12.1")

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-java-sdk-sqs" % "1.11.83",
  "org.slf4j" % "slf4j-api" % "1.7.21"
)

publishMavenStyle := true

publishTo := {
  val nexus = "https://nexus.groupl.es/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "repository/maven-snapshots")
  else
    Some("releases"  at nexus + "repository/maven-releases")
}

import ReleaseTransformations._

releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    runClean,
    runTest,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    publishArtifacts,
    setNextVersion,
    commitNextVersion,
    pushChanges)


credentials += Credentials(Path.userHome / ".ivy2" / ".meweCredentials")

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/FortyTwoEng/franz</url>
  <licenses>
    <license>
      <name>MIT</name>
      <url>http://opensource.org/licenses/MIT</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:FortyTwoEng/franz.git</url>
    <connection>scm:git:git@github.com:FortyTwoEng/franz.git</connection>
  </scm>
  <developers>
    <developer>
      <id>stkem</id>
      <name>Stephen Kemmerling</name>
      <url>https://github.com/stkem</url>
    </developer>
    <developer>
      <id>marekzebrowski</id>
      <name>Marek Żebrowski</name>
      <url>https://github.com/marekzebrowski</url>
    </developer>
  </developers>)
