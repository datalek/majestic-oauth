package rakhov.majesticoauth.services

import rakhov.majesticoauth.external.logger._
import rakhov.majesticoauth.services.oauth._
import rakhov.majesticoauth.services.identities._
import rakhov.majesticoauth.services.events._
import zio._

trait Api {
 // val api: Api.Service[Any]
}

object Api0 {



  def authorize(request: Int): ZIO[Logger with OAuth with Identities with Events, Error, Redirect] = {
    for {
      oauth <- ZIO.environment[OAuth].map(_.oauth)
      logger <- ZIO.environment[Logger].map(_.logger)
      users <- ZIO.environment[Identities].map(_.users)
      events <- ZIO.environment[Events].map(_.events)
      _ <- events.prepare().mapError(???)
      response <- oauth.authorize(???)
      _ <- events.log().mapError(???)
      zzz = ???
    } yield response
  }

}