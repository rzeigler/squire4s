package squire4s.string

import cats._
import cats.implicits._
import squire4s.{SelectAlg, Col}

trait ProjectInterpreter {
  implicit object SelectString extends SelectAlg[String] {
    def col(s: String): Col[String] = {
      val escaped = s.replace("\"", "\"\"")
      val str = '"' + escaped + '"'
      Col(str)
    }

    def select[F[_]: Functor: Reducible](cs: F[Col[String]]): String =
      cs.map(_.col).intercalate(",")
  }
}

object ProjectInterpreter extends ProjectInterpreter
