

object CaseclassEx {
  
  abstract class  prinicpal 
  { 
    val sreeni : String = "this is sreeni"
    def name : String 
  
    
    def getName : String  =  
    {
      name
    }
  }
  
  case class Person(var name : String) extends prinicpal 
  {
    
  } 
  
  
  
  def main(args : Array[String]) 
  {
       val p = Person("s")
       println(p.getName)
  }
}