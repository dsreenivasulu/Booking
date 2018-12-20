package com.Test.basic

object ConsDupList {
  
  def main(args: Array[String]): Unit = {
    
    var list1 = List(1, 2, 3)
    println("list1 ", list1)
    //o/p is (list1 ,List(1, 2, 3))
    
    println("list1.head", list1.head)
    println("list1.last", list1.last)
    println("list1.init", list1.init)
    println("list1.tail", list1.tail)    
    
    var list2 = List.range(0, 3)
    println("list2 ", list2)
    //o/p is (list2 ,List(0, 1, 2))
    
    var list3 = List.range(0, 9, 2)
    println("list3 ", list3)
    //o/p is (list3 ,List(0, 2, 4, 6, 8))
    
    var list4 = List.tabulate(3)(n1 => n1 * n1)
    println("list4 ", list4)
    //o/p is (list4 ,List(0, 1, 4))
    
    var list5 = List.tabulate(3, 3)((n1, n2) => n1 * n2)
    println("list5 ", list5)
    //o/p is (list5 ,List(List(0, 0, 0), List(0, 1, 2), List(0, 2, 4)))
    
    var list5new = list5.flatten
    println("list5new ", list5new)
    
    var list6 = List.tabulate(3, 4, 3)((n1, n2, n3) => n1 * n2)
    println("list6 ", list6)
    //o/p is (list6 ,List(List(List(0, 0, 0), List(0, 0, 0), List(0, 0, 0), List(0, 0, 0)), List(List(0, 0, 0), List(1, 1, 1), List(2, 2, 2), List(3, 3, 3)), List(List(0, 0, 0), List(2, 2, 2), List(4, 4, 4), List(6, 6, 6))))
    
    var list6new = list6.flatten
    println("list6new ", list6new)
    
    var l = list1.map(e => e + 1)
    
    println(l.mkString)
    
 }
  
   





}