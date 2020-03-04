package rakhov.majesticoauth.apis.clients.impl

import rakhov.majesticoauth.apis.clients._
import rakhov.majesticoauth.services.events._
import rakhov.majesticoauth.services.authorization._
import rakhov.majesticoauth.services.clients._
import rakhov.majesticoauth.services.identities.Identity
import zio._

class ClientApiImpl(
  authorizationService: Authorization.Service[Any],
  clientService: Clients.Service[Any]
) extends ClientApi.Api {

  private def validateAuthorization(
    authorizationService: Authorization.Service[Any],
    authorizationType: AuthorizationType
  ): ZIO[Any, ApiError, Identity] = {
    for {
      either <- authorizationService.validate(authorizationType)
        .mapError(_ => ApiError.InternalServerError())
      result <- either match {
        case Left(expired) =>
          ZIO.fail(ApiError.Forbidden())
        case Right(identity) =>
          ZIO.succeed(identity)
      }
    } yield result
  }

  private def createClient(
    clientService: Clients.Service[Any],
    clientDefinition: ClientDefinition
  ): ZIO[Any, ApiError.InternalServerError, Client] = {
    clientService.create(definition = clientDefinition)
      .mapError(_ => ApiError.InternalServerError())
  }

  private def updateClient(
    clientService: Clients.Service[Any],
    clientId: ClientId,
    clientUpdate: ClientUpdate
  ): ZIO[Any, ApiError.InternalServerError, Client] = {
    clientService.update(clientId, clientUpdate)
      .mapError(_ => ApiError.InternalServerError())
  }

  private def detailClient(
    clientService: Clients.Service[Any],
    clientId: ClientId
  ): ZIO[Any, ApiError, Client] = {
    clientService.retrieve(clientId)
      .mapError(_ => ApiError.InternalServerError())
      .flatMap({
        case Some(client) =>
          ZIO.succeed(client)
        case None =>
          ZIO.fail(ApiError.NotFound())
      })
  }

  private def sendEvent(
    env: Env,
    eventDefinition: EventDefinition
  ) = {
    env.eventService.send(event = eventDefinition)
      .mapError(_ => ApiError.InternalServerError())
  }

  def create(
    env: Env,
    authorizationType: AuthorizationType,
    clientDefinition: ClientDefinition
  ): UIO[Either[ApiError, Client]] = {
    val result = for {
      identity <- validateAuthorization(authorizationService, authorizationType)
      client <- createClient(clientService, clientDefinition)
      eventDefinition = EventDefinition(
        subject = identity,
        action = Action.ClientCreate(
          client = client.id
        )
      )
      _ <- sendEvent(env, eventDefinition)
    } yield client

    result.either
  }

  def update(
    env: Env,
    authorizationType: AuthorizationType,
    clientId: ClientId,
    clientUpdate: ClientUpdate
  ): UIO[Either[ApiError, Client]] = {
    val result = for {
      identity <- validateAuthorization(authorizationService, authorizationType)
      client <- updateClient(clientService, clientId, clientUpdate)
      eventDefinition = EventDefinition(
        subject = identity,
        action = Action.ClientUpdate(
          client = client.id
        )
      )
      _ <- sendEvent(env, eventDefinition)
    } yield client

    result.either
  }

  def detail(
    env: Env,
    authorizationType: AuthorizationType,
    clientId: ClientId
  ): UIO[Either[ApiError, Client]] = {
    val result = for {
      identity <- validateAuthorization(authorizationService, authorizationType)
      client <- detailClient(clientService, clientId)
    } yield client

    result.either
  }
}
