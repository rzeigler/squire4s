package squire4s.string

import cats.implicits._
import squire4s.Embed

trait EmbedInterpreter {
  implicit val embedString: Embed[String, String] = Embed.instance(s => {
    val escaped = s.replace("'", "''")
    show"'$escaped'"
  })

  implicit def embedNumeric[T: Numeric]: Embed[T, String] =
    Embed.instance(t => t.toString)
}
