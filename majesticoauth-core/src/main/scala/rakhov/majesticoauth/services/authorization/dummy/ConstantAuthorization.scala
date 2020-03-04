package rakhov.majesticoauth.services.authorization.dummy

import rakhov.majesticoauth.services.identities._
import rakhov.majesticoauth.services.authorization._
import zio.ZIO

final case class ConstantAuthorizationSettings(
  backingStore: Map[AuthorizationType, Identity]
)

trait ConstantAuthorization extends Authorization.Service[ConstantAuthorizationSettings] {
  def validate(authorizationType: AuthorizationType): ZIO[ConstantAuthorizationSettings, AuthorizationError, Either[Expired, Identity]] = {
    for {
      backingStore <- ZIO.access[ConstantAuthorizationSettings](_.backingStore)
      result <- backingStore.get(authorizationType) match {
        case Some(identity) =>
          ZIO.succeed(Right(identity))
        case None =>
          ZIO.fail(AuthorizationError.Error)
      }
    } yield result
  }
}
