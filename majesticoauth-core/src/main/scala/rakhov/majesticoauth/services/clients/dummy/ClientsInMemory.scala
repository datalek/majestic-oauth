package rakhov.majesticoauth.services.clients.dummy

import rakhov.majesticoauth.services.clients._
import zio.ZIO

final case class ClientInMemorySettings(
  backingStore: scala.collection.mutable.HashSet[Client],
  credentialGenerator: () => ClientCredential
)

trait ClientsInMemory extends Clients.Service[ClientInMemorySettings] {

  val database = scala.collection.mutable.HashSet[Client]()

  def retrieve(id: ClientId): ZIO[Any, ClientError, Option[Client]] = {
    ZIO.succeed(database.find(_.id == id))
  }

  def create(definition: ClientDefinition): ZIO[Any, ClientError, Client] = {
    for {

    } yield ???

    val client = Client(
      id = ClientId(java.util.UUID.randomUUID().toString),
      name = definition.name,
      credentials = Seq(credentialGenerator())
    )
    database.add(client)
    ZIO.access[ClientInMemorySettings]().succeed(client)
  }

  def remove(id: ClientId): ZIO[Any, ClientError, Client] = {
    for {
      element <- retrieve(id)
      result <- element match {
        case Some(client) =>
          database.remove(client)
          ZIO.succeed(client)
        case None =>
          ZIO.fail(ClientError())
      }
    } yield result
  }
}
