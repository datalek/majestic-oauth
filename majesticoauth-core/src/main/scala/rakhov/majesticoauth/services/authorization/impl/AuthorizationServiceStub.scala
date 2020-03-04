package rakhov.majesticoauth.services.authorization.impl

import rakhov.majesticoauth.services.authorization._
import rakhov.majesticoauth.services.identities.Identity
import zio.ZIO

class AuthorizationServiceStub(
  backingStore: Seq[(AuthorizationType, Identity)]
) extends Authorization.Service[Any] {

  def validate(authorizationType: AuthorizationType): ZIO[Any, AuthorizationError, Either[Expired, Identity]] = {
    val eventuallyIdentity = backingStore.find({
      case (authorization, _) =>
        authorization == authorizationType
    })
    val result = eventuallyIdentity match {
      case Some((_, identity)) =>
        Right(identity)
      case None =>
        Left(Expired(authorizationType))
    }
    ZIO.succeed(result)
  }
}
