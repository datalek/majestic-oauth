package rakhov.majesticoauth.apis.clients.impl

import generators.SnapshotGen
import rakhov.majesticoauth.apis.clients._
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

    zio.IO.succeed(new ClientApiImpl())
  }
}
