package rakhov.majesticoauth.services.clients

case class AccessKey(
  value: String
) extends AnyVal

case class SecretKey(
  value: String
) extends AnyVal

case class ClientCredential(
  accessKey: AccessKey,
  secretKey: SecretKey
)

case class ClientId(
  value: String
) extends AnyVal

case class Client(
  id: ClientId,
  name: String,
  credentials: Seq[ClientCredential],
  scope: Seq[String]
)
object Client {
  def clientDefintion(client: Client): ClientDefinition =
    ClientDefinition(
      name = client.name,
      scope = client.scope
    )
}

case class ClientDefinition(
  name: String,
  scope: Seq[String]
)

case class ClientError(

)