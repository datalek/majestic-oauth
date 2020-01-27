package rakhov.majesticoauth.services.identities

import zio._

trait Identities {
  def users: Identities.Service[Any]
}
object Identities {
  trait Service[R] {

    def retrieve(id: IdentityId): ZIO[R, IdentityError, Option[Identity]]

    def create(definition: IdentityDefinition): ZIO[R, IdentityError, Identity]

    def remove(id: IdentityId): ZIO[R, IdentityError, Identity]

    def update(id: IdentityId, update: IdentityUpdate): ZIO[R, IdentityError, Identity]
  }
}
