package rakhov.majesticoauth.external.logger

sealed trait Priority
object Priority {
  /* Debug messages. */
  final case object Debug extends Priority
  /* Notable information that requires no immediate action. */
  final case object Info extends Priority
  /* Something is probably wrong, and we should investigate. */
  final case object Warning extends Priority
  /* Something is wrong and immediate action is required. */
  final case object Error extends Priority
}
