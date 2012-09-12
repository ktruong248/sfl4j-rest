package loggingservice

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._

import Headers._
import Utils._

object BasicScenario {
  val minPauseTime = 10
  val source = "SampleApp"
  val runTime = 5
  val runTimeUnit = MINUTES

  val infoLogSCN = scenario("Generate INFO Log Scenario")
    .loop(
    chain.exec(
      http("create new logging event")
        .post("/")
        .headers(commonHeader)
        .fileBody("logging-service-event-json-template",
        Map("eventType" -> "log", "category" -> "INFO",
          "message" -> "sampleAll info message", "messageDetails" -> "sampleApp info message details",
          "source" -> source, "logTimeSec" -> currentMillisTimeAsString())).asJSON
        .check(status.is(201)))
      .pause(minPauseTime, MILLISECONDS)
  ).during(runTime, runTimeUnit)

  val errorLogSCN = scenario("Generate ERROR Log Scenario")
    .loop(chain.exec(
    http("create new logging event")
      .post("/")
      .headers(commonHeader)
      .fileBody("logging-service-event-json-template",
      Map("eventType" -> "log", "category" -> "ERROR",
        "message" -> "sampleAll error message", "messageDetails" -> "sampleApp error message details",
        "source" -> source, "logTimeSec" -> currentMillisTimeAsString())).asJSON
      .check(status.is(201)))
    .pause(minPauseTime, MILLISECONDS)
  ).during(runTime, runTimeUnit)
}


