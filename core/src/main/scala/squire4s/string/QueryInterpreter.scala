package squire4s.string

import cats.implicits._
import squire4s.QueryAlg
import squire4s.SelectQueryAlg
import squire4s.Query

trait QueryInterpreter {
  implicit object QueryString extends QueryAlg[String, String, String, String] {
    override def select(
        project: String
    ): SelectQueryAlg[String, String, String, String] =
      new SelectQueryAlg[String, String, String, String] {

        override def from(from: String): Query[String, String, String, String] =
          new QueryBuilder(project, from, none)

      }

  }
}

private[string] final case class QueryBuilder(
    project: String,
    from: String,
    where: Option[String]
) extends Query[String, String, String, String] {
  def render: String =
    s"SELECT $project FROM $from" + where.fold("")(w => show" WHERE $w")

  def where(where: String): Query[String, String, String, String] =
    this.copy(where = where.some)
}
