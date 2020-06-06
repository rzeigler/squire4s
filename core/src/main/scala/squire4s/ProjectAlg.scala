package squire4s

import cats._
import cats.implicits._

/**
  * The algebra of projection in query language
  *
  */
trait ProjectAlg[A] {
  def one(s: String): A
  def many[F[_]: Reducible](cs: F[A]): A
}

object ProjectAlg {
  def apply[A](implicit instance: ProjectAlg[A]): ProjectAlg[A] = instance
}
