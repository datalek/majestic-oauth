package rakhov.majesticoauth.services.events

import zio._

trait Events {
  def events: Events.Service[Any]
}

object Events {
  trait Service[R] {
    def prepare(): ZIO[R, Error, Either[Unit, Unit]]
    /* or confirm */
    def finalize(): ZIO[R, Error, Either[Unit, Unit]]
    def log(): ZIO[R, Error, Either[]]
  }
}