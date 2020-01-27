package rakhov.majesticoauth.services.authorization

import rakhov.majesticoauth.services.identities._
import zio.ZIO

trait Authorization {
  val authorization: Authorization.Service[Any]
}

object Authorization {
  trait Service[E] {
    def validate(authorizationType: AuthorizationType): ZIO[E, AuthorizationError, Identity]
  }

  import rakhov.majesticoauth.services.authorization.dummy._
  object Live extends ConstantAuthorization
}
