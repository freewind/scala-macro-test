package in.freewind

import scala.annotation.{compileTimeOnly, StaticAnnotation}
import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context

object MyMacros {

  def helloImpl(c: Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._
    val inputs = annottees.map(_.tree).toList
    val expandees = inputs match {
      case DefDef(mods, name, tparams, vparamss, tpt, rhs) :: Nil => {
        val newMethodBody = q""""Hello, " + $rhs + " !!!""""
        List(DefDef(mods, name, tparams, vparamss, tpt, newMethodBody))
      }
      case others => others
    }
    c.Expr[Any](Block(expandees, Literal(Constant(()))))
  }

  @compileTimeOnly("enable macro paradise to expand macro annotations")
  class mymacro extends StaticAnnotation {
    def macroTransform(annottees: Any*) = macro helloImpl
  }

}
