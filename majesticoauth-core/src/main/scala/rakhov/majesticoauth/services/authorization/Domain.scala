package rakhov.majesticoauth.services.authorization

sealed trait AuthorizationType
object AuthorizationType {
  final case class BasicHttp() extends AuthorizationType
  final case class AccessKeySecretKey() extends AuthorizationType
  final case class Token() extends AuthorizationType
}

final case class AuthorizationError(

)