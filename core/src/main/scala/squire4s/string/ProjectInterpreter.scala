package squire4s.string

import cats._
import cats.implicits._
import squire4s.{ProjectAlg, Col}

trait ProjectInterpreter {
  implicit object ProjectString extends ProjectAlg[String] {
    // TODO: Needs more escaping
    def col(s: String): Col[String] = Col(s)
    def project[F[_]: Functor: Reducible](cs: F[Col[String]]): String =
      cs.map(_.col).intercalate(",")
  }
}

object ProjectInterpreter extends ProjectInterpreter
