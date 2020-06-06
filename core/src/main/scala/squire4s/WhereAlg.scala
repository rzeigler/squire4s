package squire4s

/**
  * Algebra of where clauses
  *
  * Assume depends on some column type C and produces a W out
  */
trait WhereAlg[C, W] {
  def eqVal[T](lhs: C, rhs: T)(implicit ev: Lifted[T, W]): W
  def eqCol(lhs: C, rhs: C): W
  def and(x: W, y: W): W
  def or(x: W, y: W): W
  def paren(x: W): W
}

object WhereAlg {
  def apply[C, W](implicit instance: WhereAlg[C, W]): WhereAlg[C, W] = instance

  class WhereAlgOps[C](col: C) {
    def eqv[W, T](rhs: T)(implicit L: Lifted[T, W], Where: WhereAlg[C, W]): W =
      Where.eqVal(col, rhs)
  }

  implicit def toWhereAlgOps[C](col: C): WhereAlgOps[C] = new WhereAlgOps(col)
}
