package com.Test.Collections

import scala.collection.Map

object MapEx {
  
  def main(args : Array[String]) 
  {
    
  }
  
  val m = Map("a" -> 1, "b" -> 2)
  
  println("for loop")
  for((k,v) <- m)
  {
    println(k)
    println(v)
    println(m(k))
    println(k, v)
  }
  println("foreach loop")
  m.foreach(k => println(k._1, k._2))
  
  println("foreach case loop")
  
  m.foreach{case (key, value) => println(key, value)}
  
  println("additional methods")
  println( m.get("a").get)
  
  m.keys.foreach(key => println(key))
  m.values.foreach(key => println(key))
  
  println("==========================")
  val empty = m.empty
  println(empty)
  
  
  
  
}