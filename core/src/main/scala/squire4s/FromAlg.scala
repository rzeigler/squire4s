package squire4s

/**
  * The algebra of sourcing inputs in a query
  *
  */
trait FromAlg[From, Query] {
  def table(name: String, alias: Option[String]): From
  def subquery(query: Query, alias: String): From
}

object FromAlg {
  def apply[From, Query](
      implicit instance: FromAlg[From, Query]
  ): FromAlg[From, Query] = instance
}
