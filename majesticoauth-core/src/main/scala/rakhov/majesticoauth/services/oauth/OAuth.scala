package rakhov.majesticoauth.services.oauth

import zio.ZIO

trait OAuth {
  def oauth: OAuth.Service[Any]
}
object OAuth {
  trait Service[R] {
    /**
     * The authorization code grant type is used to obtain both access
     * tokens and refresh tokens and is optimized for confidential clients.
     * Since this is a redirection-based flow, the client must be capable of
     * interacting with the resource owner's user-agent.
     *
     * @see https://tools.ietf.org/html/rfc6749#section-4.1
     *
     * The implicit grant type is used to obtain access tokens (it does not
     * support the issuance of refresh tokens) and is optimized for public
     * clients known to operate a particular redirection URI.  These clients
     * are typically implemented in a browser using a scripting language
     * such as JavaScript.
     *
     * @see https://tools.ietf.org/html/rfc6749#section-4.2
     *
     * @param authorize The authorization request.
     * @return A redirect.
     */
    def authorize(authorize: Authorize): ZIO[R, Error, Redirect]

    /**
     *
     * @param grant The grant.
     * @return A token.
     */
    def token(grant: Grant): ZIO[R, Error, Token]
  }

  trait Live extends OAuth.Service[Any] {
    def authorize(authorize: Authorize): ZIO[Any, Error, Redirect] = {
      ???
    }

    def token(grant: Grant): ZIO[Any, Error, Token] = ???
  }
}
