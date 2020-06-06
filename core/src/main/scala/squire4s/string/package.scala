package squire4s

/**
  * This package contains interpreters for the squire4s algebras that target raw strings
  *
  * This is primary for diagnostic/display purposes.
  * While some minimal escaping is performed to make the output potentially useable in a
  * database client program you should not use it in production
  */
package object string
    extends ProjectInterpreter
    with FromInterpreter
    with QueryInterpreter
    with WhereInterpreter
    with EmbedInterpreter {}
