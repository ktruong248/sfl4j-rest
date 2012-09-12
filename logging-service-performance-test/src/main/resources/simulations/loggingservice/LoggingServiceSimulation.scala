package loggingservice

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._

/**
 * configure and run two scenarios to insert
 *    ERROR : 30 users and ramp up at .5 per sec
 *    INFO  : 30 users and ramp up at .5 per sec
 *
 * where each scenario will run for 5 minutes
 */
class LoggingServiceSimulation extends Simulation {

  def apply = {

    val urlBase = "http://localhost:8080/logs"

    val httpConf = httpConfig.baseURL(urlBase)

    List(
      BasicScenario.infoLogSCN.configure.users(30).ramp(15).protocolConfig(httpConf),
      BasicScenario.errorLogSCN.configure.users(30).ramp(15).protocolConfig(httpConf)
    )
  }
}
