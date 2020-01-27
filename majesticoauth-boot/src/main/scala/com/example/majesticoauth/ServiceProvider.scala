package com.example.majesticoauth

import com.example.majesticoauth.services._
import com.example.majesticoauth.services.impl._

object ServiceProvider {

  def serviceA(config: ConfigServiceA): ServiceA =
    ServiceAImpl(config)

  def serviceB(config: ConfigServiceB): ServiceB =
    ServiceBImpl(config)

}