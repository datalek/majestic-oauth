package rakhov.majesticoauth.apis.clients

trait ApiError
object ApiError {
  case class Forbidden() extends ApiError
  case class Unauthorized() extends ApiError
  case class BadRequest() extends ApiError
  case class NotFound() extends ApiError
  case class InternalServerError() extends ApiError
}
