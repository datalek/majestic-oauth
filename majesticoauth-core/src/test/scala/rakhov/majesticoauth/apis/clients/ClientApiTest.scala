package rakhov.majesticoauth.apis.clients

import zio._
import zio.test._
import zio.test.Assertion._
import rakhov.majesticoauth.services.clients._
import rakhov.majesticoauth.services.authorization._

case class Snapshot(
  token: AuthorizationType.Token
)

object ClientApiTest {

  def tests(
    snapshot: Snapshot,
    prepareScenario: Snapshot => UIO[ClientApi.Api]
  ) = {
    val env = Env(
      eventService = null
    )
    def clientDefinition = ClientDefinition(
      name = "The client name",
      scope = Seq("client_create", "client_update")
    )
    suite("The `/clients` endpoint") {
      testM("create the given client and return it") ({
        for {
          apis <- prepareScenario(snapshot)
          response <- apis.create(
            env = env,
            authorizationType = snapshot.token,
            clientDefinition = clientDefinition
          )
          actual = response.right.map(Client.clientDefintion)
          assertion = assert(actual, equalTo(Right(clientDefinition)))
        } yield assertion
      })
    }
  }
}
