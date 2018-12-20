package com.Test.basic

object firstObj {

  def main(args: Array[String]) {
        
    println("This is firt App");
    
    val list1 = List(1, 2, 1)
    
   list1 :+ 1;
    
  }
   
   //Pali 
   def pal(list : List[Any], rev : List[Any]) : Boolean = 
   {
     list.equals(rev)
   }
   
   def flattenList(list : List[Any]) : List[Any] = list flatMap 
   {
      case ms: List[Any] => flattenList(ms)
      case e => List(e);
   }
   
   
}