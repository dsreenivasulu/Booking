

object FibonacciSeriesEx {
  
  def main(args : Array[String]) 
  {
     var old = 0;
     var news = 1;
     for(l <- 1 to 10) 
     {
        var sum = old+news
        old = news
        news = sum
        println(sum)
        
     }
    
    
  }
}