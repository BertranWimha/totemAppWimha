import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "totemApp"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "com.github.ndeverge" %% "autoping-play2-plugin" % "0.1.0-SNAPSHOT"

  )



  val main = play.Project(appName, appVersion, appDependencies).settings(
   lessEntryPoints <<= baseDirectory(_ / "app" / "assets" / "stylesheets" ** "main.less")
 ).settings(  
    // Add your own project settings here
  resolvers += Resolver.url("Autoping repository", url("http://ndeverge.github.com/autoping-play2-plugin/snapshots/"))(Resolver.ivyStylePatterns)
   // javacOptions +="-Xlint:all"
    )

}
