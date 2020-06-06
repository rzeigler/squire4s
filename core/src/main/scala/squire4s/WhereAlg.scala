package squire4s

/**
  * Algebra of where clauses
  *
  * Assume depends on some column type C and produces a W out
  */
trait WhereAlg[C, W] {
  def eq[T](lhs: C, rhs: T)(implicit ev: Embed[T, W]): W
  def equiv(lhs: C, rhs: C): W
  def and(x: W, y: W): W
  def or(x: W, y: W): W
  def paren(x: W): W
}

object WhereAlg {
  def apply[C, W](implicit instance: WhereAlg[C, W]): WhereAlg[C, W] = instance
}
