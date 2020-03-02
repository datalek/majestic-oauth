package rakhov.majesticoauth.apis.clients

import rakhov.majesticoauth.services.clients._
import rakhov.majesticoauth.services.authorization._
import rakhov.majesticoauth.services.events._
import zio._

case class Env(
  authorizationService: Authorization.Service[Any],
  eventService: Events.Service,
  clientService: Clients.Service[Any]
)

object ClientApi {
  trait Api {

    def create(
      env: Env,
      authorizationType: AuthorizationType,
      clientDefinition: ClientDefinition
    ): UIO[Either[ApiError, Client]]

    def update(
      env: Env,
      authorizationType: AuthorizationType,
      clientDefinition: ClientDefinition
    ): UIO[Either[ApiError, Client]]

    def detail(
      env: Env,
      authorizationType: AuthorizationType,
      clientDefinition: ClientDefinition
    ): UIO[Either[ApiError, Client]]

  }
}