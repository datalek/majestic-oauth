package rakhov.majesticoauth.apis.clients

import generators._
import zio._
import zio.test._
import zio.test.Assertion._
import rakhov.majesticoauth.services.clients._
import rakhov.majesticoauth.services.authorization._
import rakhov.majesticoauth.services.events.impl.EventServiceStub

case class Snapshot(
  token: AuthorizationType.Token
)

object ClientApiTest {

  def tests(
    snapshot: Snapshot,
    prepareScenario: Snapshot => UIO[ClientApi.Api]
  ) = {

    def clientDefinition =
      ClientDefinitionGen.generateClientDefintion

    val env =
      Env(eventService = new EventServiceStub())

    suite("The `create` method") {
      testM("create the given client and return it") ({
        for {
          apis <- prepareScenario(snapshot)
          created <- apis.create(
            env = env,
            authorizationType = snapshot.token,
            clientDefinition = clientDefinition
          ).absolve
          actual = Client.clientDefintion(created)
        } yield assert(actual, equalTo(clientDefinition))
      })
      testM("reply with Forbidden if token is invalid") ({
        val invalidToken = AuthorizationType.Token(snapshot.token.value + "-invalid")
        for {
          apis <- prepareScenario(snapshot)
          either <- apis.create(
            env = env,
            authorizationType = invalidToken,
            clientDefinition = clientDefinition
          )
          flipped = either.swap
          apiError <- ZIO.fromEither(flipped)
        } yield assert(apiError, equalTo(ApiError.Forbidden()))
      })
    }
  }
}
