package squire4s

import cats._
import cats.data._
import cats.implicits._

final case class Col[A](col: A)

/**
  * The algebra of projection in query language
  *
  */
trait SelectAlg[A] {
  def col(s: String): Col[A]
  def select[F[_]: Functor: Reducible](cs: F[Col[A]]): A
  def selectJust(c: Col[A]): A = select(NonEmptyList.of(c))
}

object SelectAlg {
  def apply[A](implicit instance: SelectAlg[A]): SelectAlg[A] = instance
}
