package squire4s.string

import squire4s.FromAlg

trait FromInterpreter {
  implicit object FromString extends FromAlg[String] {

    override def table(name: String): String = name
  }
}
