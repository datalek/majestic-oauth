package com.example.majesticoauth

import com.example.majesticoauth.services.impl._

object ConfigProvider {
  def parseConfigServiceA(config: Config): Either[String, ConfigServiceA]
  def parseConfigServiceB(config: Config): Either[String, ConfigServiceB]
}