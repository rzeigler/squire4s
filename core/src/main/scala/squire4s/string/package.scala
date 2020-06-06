package squire4s

/**
  * This package contains interpreters for the squire4s algebras that target
  * raw string output.
  *
  */
package object string
    extends ProjectInterpreter
    with FromInterpreter
    with QueryInterpreter
    with WhereInterpreter
    with LiftedInterpreter {}
