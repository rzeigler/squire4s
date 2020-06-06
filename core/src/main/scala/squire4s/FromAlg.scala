package squire4s

/**
  * The algebra of sourcing inputs in a query
  *
  */
trait FromAlg[A] {
  def table(name: String): A
}

object FromAlg {
  def apply[A](implicit instance: FromAlg[A]): FromAlg[A] = instance
}
