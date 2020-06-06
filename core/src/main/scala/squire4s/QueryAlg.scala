package squire4s

/**
  * Initial process of constructing a QueryAlg
  *
  * This exists to allow slightly more natural invocation
  */
trait QueryAlg[Q, Project, From, Where] {
  def select(project: Project): SelectQueryAlg[Q, Project, From, Where]
}

object QueryAlg {
  def apply[Q, Project, From, Where](
      implicit instance: QueryAlg[Q, Project, From, Where]
  ): QueryAlg[Q, Project, From, Where] = instance
}

trait SelectQueryAlg[Q, Project, From, Where] {
  def from(from: From): Query[Q, Project, From, Where]
}

trait Query[Q, Project, From, Where] {
  def render: Q
  def where(where: Where): Query[Q, Project, From, Where]
}
