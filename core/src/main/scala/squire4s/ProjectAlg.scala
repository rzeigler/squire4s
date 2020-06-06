package squire4s

import cats._
import cats.implicits._

final case class Col[A](col: A)

/**
  * The algebra of projection in query language
  *
  */
trait ProjectAlg[A] {
  def col(s: String): Col[A]
  def project[F[_]: Functor: Reducible](cs: F[Col[A]]): A
  def projectOne(c: Col[A]): A = c.col
}

object ProjectAlg {
  def apply[A](implicit instance: ProjectAlg[A]): ProjectAlg[A] = instance
}
