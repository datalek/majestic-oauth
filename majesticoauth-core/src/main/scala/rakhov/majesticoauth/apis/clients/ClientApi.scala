package rakhov.majesticoauth.apis.clients

import rakhov.majesticoauth.services.clients._
import rakhov.majesticoauth.services.authorization._
import zio.ZIO

object ClientApi {

  def createClient(authorizationType: AuthorizationType, client: ClientDefinition): ZIO[Clients with Authorization, AuthorizationError, Client] = {
    for {
      authorization <- ZIO.access[Authorization](_.authorization)
      clients <- ZIO.access[Clients](_.clients)
      _ <- authorization.validate(authorizationType)
      newClient <- clients.create(client).mapError(clientError => AuthorizationError())
    } yield newClient
  }

  def retrieveClient(authorizationType: AuthorizationType, id: ClientId): ZIO[Clients with Authorization, AuthorizationError, Option[Client]] = {
    for {
      authorization <- ZIO.access[Authorization](_.authorization)
      clients <- ZIO.access[Clients](_.clients)
      _ <- authorization.validate(authorizationType)
      newClient <- clients.retrieve(id).mapError(clientError => AuthorizationError())
    } yield newClient
  }

  def removeClient(authorizationType: AuthorizationType, id: ClientId): ZIO[Clients with Authorization, AuthorizationError, Client] = {
    for {
      authorization <- ZIO.access[Authorization](_.authorization)
      clients <- ZIO.access[Clients](_.clients)
      _ <- authorization.validate(authorizationType)
      newClient <- clients.remove(id).mapError(clientError => AuthorizationError())
    } yield newClient
  }

}