val appName = "ChangeMe"
val testScope = "test, it"

val compile: Seq[ModuleID] = Seq(
  guice
)

val test: Seq[ModuleID] = Seq(
  "com.github.tomakehurst" % "wiremock-jre8" % "2.23.2" % testScope,
  "org.jsoup" % "jsoup" % "1.10.3" % testScope,
  "org.scalatest" %% "scalatest" % "3.0.4" % testScope,
  "org.scalamock" %% "scalamock" % "4.1.0" % testScope,
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.0" % testScope
)

lazy val appDependencies: Seq[ModuleID] = compile ++ test

lazy val coverageSettings: Seq[Setting[_]] = {
  import scoverage.ScoverageKeys

  val excludedPackages = Seq(
    "<empty>",
    "Reverse.*",
    "app.*",
    "router.*"
  )

  Seq(
    ScoverageKeys.coverageExcludedPackages := excludedPackages.mkString(";"),
    ScoverageKeys.coverageMinimum := 95,
    ScoverageKeys.coverageFailOnMinimum := false,
    ScoverageKeys.coverageHighlighting := true
  )
}

lazy val project: Project = Project(appName, file("."))
  .enablePlugins(Seq(PlayScala): _*)
  .settings(coverageSettings: _*)
  .settings(
    PlayKeys.playDefaultPort := 9000,
    scalaVersion := "2.12.8",
    libraryDependencies ++= appDependencies
  )
  .configs(IntegrationTest)
  .settings(inConfig(IntegrationTest)(Defaults.itSettings): _*)
  .settings(
    Keys.fork in IntegrationTest := false,
    unmanagedSourceDirectories in IntegrationTest := (baseDirectory in IntegrationTest) (base => Seq(base / "it")).value,
    parallelExecution in IntegrationTest := false
  )
