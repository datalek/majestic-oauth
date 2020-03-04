package rakhov.majesticoauth.apis.clients.impl

import generators.SnapshotGen
import rakhov.majesticoauth.apis.clients._
import rakhov.majesticoauth.services.authorization.impl.AuthorizationServiceStub
import rakhov.majesticoauth.services.clients.impl.ClientServiceImpl
import zio.test._

object ClientApiImplTest extends DefaultRunnableSpec(
  suite("ClientApiImpl")(
    ClientApiTest.tests(
      snapshot = SnapshotGen.generateSnapshot,
      prepareScenario = new ClientApiStub().prepareScenario
    )
  )
)

class ClientApiStub() {
  def prepareScenario(snapshot: Snapshot) = {

    val authorizationService =
      new AuthorizationServiceStub(
        backingStore = Seq()
      )

    val clientService =
      new ClientServiceImpl()

    val api = new ClientApiImpl(
      authorizationService = authorizationService,
      clientService = clientService
    )
    zio.IO.succeed(api)
  }
}
