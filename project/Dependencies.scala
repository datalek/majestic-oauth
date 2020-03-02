import sbt._

object Dependencies {
  val commonResolvers = Seq(
    "Typesafe Repo" at "https://repo.typesafe.com/typesafe/releases/"
  )

  object Typesafe {
    val config = "com.typesafe" % "config" % "1.4.0"
  }

  object Zio {
    val version = "1.0.0-RC17"
    val core = "dev.zio" %% "zio" % version
    val test = "dev.zio" %% "zio-test" % version
    val testSbt = "dev.zio" %% "zio-test-sbt" % version
  }
}
