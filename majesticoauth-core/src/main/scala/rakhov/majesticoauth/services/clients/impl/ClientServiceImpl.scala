package rakhov.majesticoauth.services.clients.impl

import rakhov.majesticoauth.services.clients._
import zio._

class ClientServiceImpl(

) extends Clients.Service[Any] {

  private var backingStore: Map[ClientId, Client] =
    Map()

  def retrieve(id: ClientId): IO[ClientError, Option[Client]] = {
    ZIO.succeed(backingStore.get(id))
  }

  def create(definition: ClientDefinition): IO[ClientError, Client] = {
    val client = Client(
      id = ClientId(java.util.UUID.randomUUID().toString),
      name = definition.name,
      credentials = Nil,
      scope = definition.scope
    )
    backingStore = backingStore + (client.id -> client)
    ZIO.succeed(client)
  }

  def update(id: ClientId, update: ClientUpdate): IO[ClientError, Client] = {
    for {
      client <- retrieve(id)
      result = client match {
        case Some(client) =>
          val updated = client.copy(
            name = update.name,
            scope = update.scope
          )
          backingStore = backingStore.updated(client.id, updated)
          Right(updated)
        case None =>
          Left(ClientError())
      }
      response <- ZIO.succeed(result).absolve
    } yield response
  }

  def remove(id: ClientId): IO[ClientError, Client] = {
    for {
      client <- retrieve(id)
      result <- ZIO.fromOption(client)
        .mapError(_ => ClientError())
    } yield {
      backingStore = backingStore - id
      result
    }

  }
}
