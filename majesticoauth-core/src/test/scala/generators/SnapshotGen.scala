package generators

import rakhov.majesticoauth.apis.clients._
import rakhov.majesticoauth.services.authorization.AuthorizationType

object SnapshotGen {

  def generateSnapshot: Snapshot = {
    Snapshot(
      token = AuthorizationType.Token(value = scala.util.Random.nextString(10))
    )
  }

}
