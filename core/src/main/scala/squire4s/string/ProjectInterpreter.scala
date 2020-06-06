package squire4s.string

import cats._
import cats.implicits._
import squire4s.ProjectAlg

trait ProjectInterpreter {
  implicit object ProjectString extends ProjectAlg[String] {
    // TODO: Needs more escaping
    def one(s: String): String = s
    def many[F[_]: Reducible](cs: F[String]): String = cs.intercalate(",")
  }
}

object ProjectInterpreter extends ProjectInterpreter
