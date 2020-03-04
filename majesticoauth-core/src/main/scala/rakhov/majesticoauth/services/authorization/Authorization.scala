package rakhov.majesticoauth.services.authorization

import rakhov.majesticoauth.services.identities._
import zio.ZIO

trait Authorization {
  val authorization: Authorization.Service[Any]
}

object Authorization {
  trait Service[E] {
    /**
     * Given an authorization return the connected identity.
     *
     * @param authorizationType The type of the authorization.
     * @return The identity or the an error.
     */
    def validate(authorizationType: AuthorizationType): ZIO[E, AuthorizationError, Either[Expired, Identity]]
  }

  import rakhov.majesticoauth.services.authorization.dummy._
  object Live extends ConstantAuthorization
}
