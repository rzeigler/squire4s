package squire4s.string

import cats.implicits._
import squire4s.WhereAlg
import squire4s.Lifted

trait WhereInterpreter {
  implicit object WhereString extends WhereAlg[String, String] {

    // For the purposes of handling escaping:
    // we assume we are embedded and the column algebra or lifted handled it

    override def eqVal[T](lhs: String, rhs: T)(
        implicit ev: Lifted[T, String]
    ): String = show"$lhs = ${ev.lift(rhs)}"

    override def eqCol(lhs: String, rhs: String): String = show"$lhs = $rhs"

    override def and(x: String, y: String): String = show"$x AND $y"

    override def or(x: String, y: String): String = show"$x OR $y"

    override def paren(x: String): String = show"($x)"

  }
}
