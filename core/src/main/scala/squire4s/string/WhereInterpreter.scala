package squire4s.string

import cats.implicits._
import squire4s.WhereAlg
import squire4s.Embed
import squire4s.{WhereAlg, Embed, Col}

trait WhereInterpreter {
  implicit object WhereString extends WhereAlg[String, String] {

    // For the purposes of handling escaping:
    // we assume we are embedded and the column algebra or lifted handled it
    override def eq[T](lhs: Col[String], rhs: T)(
        implicit ev: T Embed String
    ): String = show"${lhs.col} = ${ev.embed(rhs)}"

    override def equiv(lhs: Col[String], rhs: Col[String]): String =
      show"${lhs.col} = ${rhs.col}"

    override def and(x: String, y: String): String = show"$x AND $y"

    override def or(x: String, y: String): String = show"$x OR $y"

    override def paren(x: String): String = show"($x)"

  }
}
