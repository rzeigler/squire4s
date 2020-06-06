package squire4s

/**
  * Algebra of where clauses
  *
  * Assume depends on some column type C and produces a W out
  */
trait WhereAlg[ColT, Where] {
  def eq[T](lhs: Col[ColT], rhs: T)(implicit ev: Embed[T, Where]): Where
  def equiv(lhs: Col[ColT], rhs: Col[ColT]): Where
  def and(x: Where, y: Where): Where
  def or(x: Where, y: Where): Where
  def paren(x: Where): Where
}

object WhereAlg {
  def apply[Col, Where](
      implicit instance: WhereAlg[Col, Where]
  ): WhereAlg[Col, Where] =
    instance
}
