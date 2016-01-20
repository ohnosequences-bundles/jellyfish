Nice.scalaProject

name := "jellyfish"
organization := "ohnosequences-bundles"
description := "A bundle for jellyfish tool"

publishBucketSuffix := "era7.com"

resolvers := Seq(
  "Era7 public maven releases"  at s3("releases.era7.com").toHttps(s3region.value.toString),
  "Era7 public maven snapshots" at s3("snapshots.era7.com").toHttps(s3region.value.toString)
) ++ resolvers.value

libraryDependencies += "ohnosequences" %% "statika" % "2.0.0-M4"
