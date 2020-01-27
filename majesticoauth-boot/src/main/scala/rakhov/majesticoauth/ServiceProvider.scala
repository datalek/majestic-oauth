package rakhov.majesticoauth

import rakhov.majesticoauth.services._
import rakhov.majesticoauth.services.impl._

object ServiceProvider {

  def serviceA(config: ConfigServiceA): ServiceA =
    ServiceAImpl(config)

  def serviceB(config: ConfigServiceB): ServiceB =
    ServiceBImpl(config)

}