package rakhov.majesticoauth.services.events.impl

import rakhov.majesticoauth.services.events._
import zio.IO

class EventServiceStub(

) extends Events.Service {

  private var backingStore: List[Event] = Nil

  def events =
    backingStore

  def send(event: EventDefinition): IO[EventError, Event] = {
    val result = Event(
      id = EventId(scala.util.Random.nextString(10)),
      subject = event.subject,
      action = event.action
    )
    backingStore =
      result :: backingStore
    IO.succeed(result)
  }

}
