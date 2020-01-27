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
  credentials: Seq[ClientCredential]
)

case class ClientDefinition(
  name: String
)

case class ClientError(

)