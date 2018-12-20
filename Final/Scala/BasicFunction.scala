package com.Test.basic

import java.util.function.Predicate

object BasicFunction {
  
  println("hello")
  
  println(2.equals(2))
  
  println(2 == 2)
  
  println(2.max(3))
  println(4.max(3))
  
  var range = 4.until(10)
  
  println(range)
  
  
  println(range.start)
  println(range.isEmpty)
  println(range.end)
  println(range.last)
  println(range.head)
  println(range.tail)
  println(range.reverse)
  println(range.size)
  println(range.fold(3)((x, y) => x+y ))
  println(range.reduce((x, y) => x+y ))
  
  def main(args : Array[String]) 
  {
    
    
    
  }
  
}