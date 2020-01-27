package rakhov.majesticoauth.external.logger

import zio.ZIO

trait Logger {
  val logger: Logger.Service[Any]
}

object Logger {
  trait Service[R] {
    def log(priority: Priority, message: String): ZIO[R, Nothing, Unit]
  }
}
