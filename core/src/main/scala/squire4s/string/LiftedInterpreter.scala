package squire4s.string

import cats.implicits._
import squire4s.Lifted

trait LiftedInterpreter {
  implicit object LiftedString extends Lifted[String, String] {
    // TODO: Escaping
    override def lift(a: String): String = show"'$a'"
  }

  implicit def liftedNumeric[T: Numeric]: Lifted[T, String] =
    new Lifted[T, String] {
      // Assume because its a number it is sql safe...
      def lift(a: T): String = a.toString
    }
}
