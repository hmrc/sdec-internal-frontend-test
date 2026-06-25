import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "org.scalatest"     %% "scalatest"               % "3.2.20",
    "org.scalatestplus" %% "selenium-4-17"           % "3.2.18.0",
    "io.rest-assured"    % "rest-assured"            % "6.0.0",
    "uk.gov.hmrc.mongo" %% "hmrc-mongo-test-play-30" % "2.12.0",
    "uk.gov.hmrc"       %% "ui-test-runner"          % "0.54.0",
    "org.playframework" %% "play-test"               % "3.0.11" exclude ("ch.qos.logback", "logback-classic")
  ).map(_ % "test")

}
