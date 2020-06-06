package squire4s.string

import cats.implicits._
import squire4s.FromAlg

trait FromInterpreter {
  implicit object FromString extends FromAlg[String, String] {

    override def table(name: String, alias: Option[String]): String =
      alias.fold(name)(a => show"$name $a")

    override def subquery(query: String, alias: String): String =
      show"($query) $alias"
  }
}
