package rakhov.majesticoauth.services.authorization

sealed trait AuthorizationType
object AuthorizationType {
  final case class Token(value: String) extends AuthorizationType
}

final case class Expired(authorizationType: AuthorizationType)

sealed trait AuthorizationError
object AuthorizationError {
  final case object Error extends AuthorizationError
}