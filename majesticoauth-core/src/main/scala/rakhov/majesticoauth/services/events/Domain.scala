package rakhov.majesticoauth.services.events

sealed trait Event
object Event {
  final case class Login(

  ) extends Event
  final case class Register(

  ) extends Event
}
