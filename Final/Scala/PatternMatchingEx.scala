import scala.util.Random


object PatternMatchingEx {
  
  def main (args : Array[String]) 
  {
    val list = Random.nextInt(10)
    list match 
    {
      case 0 => println("1")
      
    }
    
  }
}