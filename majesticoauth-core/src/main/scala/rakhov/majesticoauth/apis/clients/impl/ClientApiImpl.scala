package rakhov.majesticoauth.apis.clients.impl

import rakhov.majesticoauth.apis.clients._
import rakhov.majesticoauth.services.authorization._
import rakhov.majesticoauth.services.clients._
import zio._

class ClientApiImpl() extends ClientApi.Api {

  def create(
    env: Env,
    authorizationType: AuthorizationType,
    clientDefinition: ClientDefinition
  ): UIO[Either[ApiError, Client]] = {
    for {
      identity <- env.authorizationService.validate(authorizationType)
      client <- env.clientService.create(clientDefinition)
      prepareEvent <- env.eventService.prepare()
      event <- env.eventService.finalize(in = ())
    } yield client
    ???
  }

  def update(
    env: Env,
    authorizationType: AuthorizationType,
    clientDefinition: ClientDefinition): UIO[Either[ApiError, Client]] = {
    ???
  }

  def detail(
    env: Env,
    authorizationType: AuthorizationType,
    clientDefinition: ClientDefinition
  ): UIO[Either[ApiError, Client]] = {
    ???
  }
}
