package rakhov.majesticoauth.services.events

import rakhov.majesticoauth.services.clients._
import rakhov.majesticoauth.services.identities._

case class EventId(
  value: String
) extends AnyVal

case class Event(
  id: EventId,
  subject: Identity,
  action: Action
)
case class EventDefinition(
  subject: Identity,
  action: Action
)

sealed trait Action
object Action {
  final case class ClientCreate(
    client: ClientId
  ) extends Action
  final case class ClientUpdate(
    client: ClientId
  ) extends Action
}

case class EventError()