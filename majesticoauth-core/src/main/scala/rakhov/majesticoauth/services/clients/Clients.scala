package rakhov.majesticoauth.services.clients

import zio._

trait Clients {
  val clients: Clients.Service[Any]
}

object Clients {
  trait Service[R] {

    def retrieve(id: ClientId): ZIO[R, ClientError, Option[Client]]

    def create(definition: ClientDefinition): ZIO[R, ClientError, Client]

    def remove(id: ClientId): ZIO[R, ClientError, Client]

  }

//  import rakhov.majesticoauth.services.clients.dummy._
//  object Live extends ClientsInMemory
}
