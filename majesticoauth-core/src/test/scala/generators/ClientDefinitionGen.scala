package generators

import rakhov.majesticoauth.services.clients.ClientDefinition

object ClientDefinitionGen {
  def generateClientDefintion: ClientDefinition =
    ClientDefinition(
      name = "The client name",
      scope = Seq("client_create", "client_update")
    )
}
