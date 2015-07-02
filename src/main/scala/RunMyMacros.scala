import in.freewind.MyMacros.mymacro

object RunMyMacros extends App {

  @mymacro
  def name(): String =  "Freewind"

  println(name())


}
