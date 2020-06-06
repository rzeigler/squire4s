package squire4s

/**
  * The algebra for lifting values into the 'dynamic' parts of a query.
  *
  * Think filling ? in a parameterized query
  */
trait Lifted[From, Into] {
  def lift(a: From): Into
}

object Lifted {
  def apply[From, Into](implicit instance: From Lifted Into): From Lifted Into =
    instance
}
