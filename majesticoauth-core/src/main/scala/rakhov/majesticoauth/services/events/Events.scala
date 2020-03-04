package rakhov.majesticoauth.services.events

import zio._

object Events {
  trait Service {

    def send(event: EventDefinition): IO[EventError, Event]

  }
}