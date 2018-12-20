

object TraitTestEx {
 
  trait A 
  {
    def isNotEqual(x : Any) : Boolean = !isEqual(x)
    def isEqual(x : Any) : Boolean 
  }
  
  class B(val y : Int) extends A 
  {
    def isEqual(x : Any) : Boolean = 
    {
      x.isInstanceOf[B] &&  
      x.asInstanceOf[B].y == y 
    } 
  }
  
  def main(args : Array[String]) 
  {
    val b = new B(1)
    val b2 = new B(2)
    println(b.isEqual(b2))
    println(b.isNotEqual(b2))
    val b3 = new B(1)
    println(b.isEqual(b3))
    println(b.isNotEqual(b3))
    
  }
  
  
}