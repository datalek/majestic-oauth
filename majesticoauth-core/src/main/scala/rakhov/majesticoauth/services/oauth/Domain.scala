package rakhov.majesticoauth.services.oauth

case class Scope(
  value: String
)

sealed trait Authorize
object Authorize {
  final case class AuthorizationCodeGrant(
    // response_type = "code"
    clientId: String,
    redirectUri: Option[java.net.URI],
    scope: Option[Seq[Scope]],
    state: String
  ) extends Authorize

  final case class ImplicitGrant(
    // response_type = "token"
    clientId: String,
    redirectUri: Option[java.net.URI],
    scope: Option[Seq[Scope]],
    state: String
  ) extends Authorize
}

sealed trait Grant
object Grant {
  final case class AuthorizationCodeGrant(
    // grant_type = "authorization_code"
    code: String,
    redirect_uri: Option[java.net.URI],
    clientId: String
  )

  final case class ResourceOwnerPasswordCredentialsGrant(
    // grant_type = "password"
    username: String,
    password: String,
    scope: Option[Seq[Scope]]
  ) extends Grant

  final case class ClientCredentialsGrant(
    // grant_type = "client"
    scope: Option[Seq[Scope]]
  ) extends Grant
}

final case class Error(
  error: Error.ErrorCode,
  description: Option[String],
  uri: Option[java.net.URI],
  state: Option[String]
)
object Error {
  sealed trait ErrorCode
  final case object InvalidRequest
  final case object InvalidClient
  final case object InvalidGrant
  final case object UnauthorizedClient
  final case object UnsupportedGrantType
  final case object InvalidScope
}

final case class Token(
  accessToken: String,
  tokenType: String,
  expiresIn: Int,
  refreshToken: String
)

final case class Redirect(
  location: java.net.URI
)
