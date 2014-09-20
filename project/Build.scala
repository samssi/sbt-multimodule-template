import sbt._
import sbt.Keys._
import scala._
import com.github.siasia.WebPlugin._

object MasterBuild extends Build {
  name := "mydddproject"

  lazy val buildSettings = Seq(
    organization := "samssi",
    version:= "1.0-SNAPSHOT",
    scalaVersion := "2.10.0"
  )

  override lazy val settings = super.settings ++ buildSettings ++ Seq(
    resolvers ++= Seq(
      "Sonatype releases" at "http://oss.sonatype.org/content/repositories/releases"
    )
  )

  lazy val root = Project(
    id = "parent",
    base = file(".")
  ).aggregate(application)

  lazy val application = {
    Project(
      id = "application",
      base = file("application"),
      settings = generalSettings ++ serverSettings ++ Seq(
        libraryDependencies ++= Seq(
        )
      )
    )
  }

  lazy val generalSettings = {
    Defaults.defaultSettings ++ Seq(
      libraryDependencies ++= Seq(
        "junit" % "junit" % "4.8.2" % "test",
        "log4j" % "log4j" % "1.2.16",
        "org.slf4j" % "slf4j-log4j12" % "1.7.2",
        "org.specs2" %% "specs2" % "1.14" % "test"
      )
    )
  }

  lazy val serverSettings = {
    webSettings ++ Seq(
      libraryDependencies ++= Seq(
        "org.eclipse.jetty" % "jetty-server" % "8.1.15.v20140411" % "container",
        "org.eclipse.jetty" % "jetty-webapp" % "8.1.15.v20140411" % "container",
        "org.eclipse.jetty" % "jetty-servlets" % "8.1.15.v20140411" % "container",
        "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "provided" artifacts Artifact("javax.servlet", "jar", "jar")
      )
    ) ++ buildSettings
  }
}
