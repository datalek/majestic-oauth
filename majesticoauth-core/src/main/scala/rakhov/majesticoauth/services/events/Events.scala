package rakhov.majesticoauth.services.events

import zio._

object Events {
  trait Service {
    def prepare(): IO[Error, Either[Unit, Unit]]
    /* or confirm */
    def finalize(in: Unit): IO[Error, Either[Unit, Unit]]

    def log(): IO[Error, Either[Any, Any]]
  }
}