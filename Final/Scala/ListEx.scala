package com.Test.Collections

object ListEx extends App{
  
   var list = List(1, 2, 3)
  
   println("Head ", list.head)
   
   
   //How to construct List 
    
   //======1)using :: infix operator or List constructor operator i.e lisp style 
    
   val ex1 = 1 :: 2 :: 3  :: Nil
   //println(ex1) //List(1, 2, 3)
   
   val ex2 = list.::(4).::(5)
   //println(ex2) //List(5, 4, 1, 2, 3) 
   
   val ex3 = (4) :: list
   //println(ex3) // List(4, 1, 2, 3)
   
   //======2)using java style by creating List object 
   
   val ex2_1 = List(1, 2, 3)
   
   val ex2_2 = (0 to 3).toList
   //println(ex2_2) //List(0, 1, 2, 3)
   
   val ex2_3 =  (0 to 5 by 2).toList
   //println(ex2_3) //List(0, 2, 4)
   
   val ex2_4 =  (0 until 5).toList
   //println(ex2_4) //List(0, 1, 2, 3, 4)
   
   val ex2_5 =  (0 until 5 by 3).toList
   //println(ex2_5) //List(0, 3)
   
   val ex2_6 = Map(1 -> "Hi").toList
   //println(ex2_6) //List((1,Hi))
   
   val ex2_7 = Map(1 -> "Hi", 2 -> "Hello").toList
   //println(ex2_7)//List((1,Hi), (2,Hello))
   
   val ex2_8 = Set(1, 2, 3).toList
   //println(ex2_8)//List(1, 2, 3)
   
   val ex2_9 = Array(1, 2, 3).toList
   //println(ex2_9)//List(1, 2, 3)
   
   val tuples = (1, 2, 3)
   val ext2_10 = tuples.productIterator.toList
   //println(ext2_10)//List(1, 2, 3)
   
   val ex2_11 = List[Any](1, 2.0, "Hello", false)
   //ex2_11.foreach(println) //1
                          //2.0
                          //Hello
                          //false
   //println(ext2_11) //error i.e not found : value ex2_11
   
   //======3)using range method (range is nothing but until)
   val ex3_1 = List.range(1, 4) 
   //println(ex3_1) //List(1, 2, 3)
   
   val ex3_2 = List.range(1, 5, 2)
   //println(ex3_2)//List(1, 3)
   
   //======4)using fill method 
   val ex4_1 = List.fill(3)("Hello")
   //println(ex4_1) //List(Hello, Hello, Hello)
   
   val ex4_2 = List.fill(1, 1)("Hello")
   //println(ex4_2) //List(List(Hello))
   
   val ex4_3 = List.fill(1, 1, 1)("Hello")
   //println(ex4_3) //List(List(List(Hello)))
   
   val ex4_4 = List.fill(1, 1, 1, 1)("Hello")
   //println(ex4_4) //List(List(List(List(Hello))))
   
   val ex4_5 = List.fill(1, 1, 1, 1, 1)("Hello")
   //println(ex4_5) //List(List(List(List(List(Hello)))))
   
   //======5)using tabulate method
   val ex5_1 = List.tabulate(2)(x=>x*2)
   //println(ex5_1) //List(0, 2)
   
   
   //Methods 
   
   //How to prepend an element into List
   val plist1 = List(1, 2, 4)
   val plist2 = 6 :: plist1
   //println(plist2) //List(6, 1, 2, 4)
   
   val plist3 = plist1.::(7)
  // println(plist3) //List(7, 1, 2, 4)
   
   val plist4 = 8 +: plist1
   //println(plist4) //List(8, 1, 2, 4)
   
   val plist5 = plist1.+:(9)
   //println(plist5) //List(9, 1, 2, 4)
   
   val listAggMethods = List(7,2,3,4,5)
   //println(listAggMethods.max) //7
   //println(listAggMethods.min) //2
   //println(listAggMethods.sum) //15
   //println(listAggMethods.last) //5
   //println(listAggMethods.head) //7
   //println(listAggMethods.tail) //List(2, 3, 4, 5)
   
   val dropped_list_1 = listAggMethods.drop(2)
   //println(dropped_list_1) //List(3, 4, 5)
   val dropped_list_2 = listAggMethods.drop(10)
   //println(dropped_list_2) // List()
   val drooped_list_while = listAggMethods.dropWhile(x => x > 8) 
   //returns the longest suffix of this list whose first element does not satisfy the predicate p.
   //println(drooped_list_while) //List(7, 2, 3, 4, 5)
   val drooped_list_while_2 = listAggMethods.dropWhile(x => x > 3) 
   //println(drooped_list_while_2) //List(2, 3, 4, 5)
   
   val reversed_list = listAggMethods.reverse
   //println(reversed_list) //List(5, 4, 3, 2, 7)
   val reversed_list_d = listAggMethods.reverse_:::(plist1)
   //Adds the elements of a given list in reverse order in front of this list
   //println(reversed_list_d) //List(4, 2, 1, 7, 2, 3, 4, 5)
   
   val take_list_1 = listAggMethods.take(2)
   //println(take_list_1) //List(7, 2)
   val take_list_2 =  listAggMethods.take(10)
   //println(take_list_2) //List(7, 2, 3, 4, 5)
   val take_right_list = listAggMethods.takeRight(2)
   //println(take_right_list) //List(4, 5)
   val take_while_list1 = listAggMethods.takeWhile(x => x >8)
   //the longest prefix of this list whose elements all satisfythe predicate p.
   //println(take_while_list1) //List()
   val take_while_list2 = listAggMethods.takeWhile(x => x >3)
   //println(take_while_list2) //List(7)
   
   val count_list = listAggMethods.count(x => x ==3)
   //println(count_list) //1
   val list_size = listAggMethods.size
   //println(list_size) //5
   
   val dup_list = List(1, 2, 2, 3)
   val unique_list = dup_list.distinct 
   //println(unique_list) //List(1, 2, 3)
   val diff_list = dup_list.diff(listAggMethods)
   //println(diff_list) //List(1, 2)
   val diff_list1 = unique_list.diff(listAggMethods)
   //println(diff_list1) //List(1)
   val diff_list2 = listAggMethods.diff(unique_list)
   //println(diff_list2) //List(7, 4, 5)
   val uninon_list_1 =  unique_list.union(listAggMethods)
   //println(uninon_list_1) //List(1, 2, 3, 7, 2, 3, 4, 5)
   val uninon_list_2 =  listAggMethods.union(unique_list)
   //println(uninon_list_2) //List(7, 2, 3, 4, 5, 1, 2, 3)
   
   
   val list2 = List("Plain Donut", 1.5, "Strawberry Donut", 2.0, "Glazed Donut", 2.5)
   
   //collect is filter + map
   val col_list1 = list2.collect{case name : Double => name}
   //println(col_list1) //List(1.5, 2.0, 2.5)
   val col_list2 =  list2.collect{case name : Double => name > 1.5}
   //println(col_list2) //List(false, true, true)
   val col_list3 =  list2.collect{case name : String => name}
   //println(col_list3) //List(Plain Donut, Strawberry Donut, Glazed Donut)
   val col_list4 =  list2.collect{case name : String => name.startsWith("P")}
   //println(col_list4) //List(true, false, false)
   val col_list5 =  list2.collect{case name : String => name 
                 case name : Double => name
                 }
   //println(col_list5) //List(Plain Donut, 1.5, Strawberry Donut, 2.0, Glazed Donut, 2.5)
   
   val col_list6 = list2.collect{case name : Double if name > 2.0 => name + 1}
   //the above is equvilaent to filter + map
   //println(col_list6)//List(3.5)
     
   val list3 = List(1, 4, 2, 3)
   val sorted_list1 = list3.sorted 
   //println(sorted_list1) //List(1, 2, 3, 4)
   val sorted_list2 = list3.sorted(Ordering.Int.reverse) //Ordering.DataType.reverse
   //println(sorted_list2) //List(4, 3, 2, 1)
   
   val sort_with_list1 = list3.sortWith((x, y) => x > y)
   //println(sort_with_list1) //List(4, 3, 2, 1)
   val sort_with_list2 = list3.sortWith((x, y) => x < y)
   //println(sort_with_list2) //List(1, 2, 3, 4)
   
   case class Emp(id: Int,name: String,salary: Double) extends Ordered[Emp] {
     def compare(that: Emp) = this.name compare that.name
  }
   val firstEmp = Emp(1, "michael", 12000.00)
   val secondEmp = Emp(2, "james", 12000.00)
   val thirdEmp = Emp(3, "shaun", 12000.00)
   
   val empList = List(firstEmp,secondEmp,thirdEmp) 
   //println(empList.sorted) // List(Emp(2,james,12000.0), Emp(1,michael,12000.0), Emp(3,shaun,12000.0))
   
   //If you do not extend Ordered trait
   //println(empList.sorted) // compile time error
   
   
   //sort by name
   //println(empList.sortBy(_.name)) //List(Emp(1,james,12000.0), Emp(3,michael,12000.0), Emp(4,michael,11000.0), Emp(5,michael,15000.0), Emp(2,shaun,12000.0))
   
   //sort in descending  by salary
   //println(empList.sortBy(_.salary)(Ordering[Double].reverse)) //List(Emp(5,michael,15000.0), Emp(1,james,12000.0), Emp(2,shaun,12000.0), Emp(3,michael,12000.0), Emp(4,michael,11000.0))
   
   
   //multiple attributes 
   println(empList.sortBy(empList =>(empList.name,empList.salary))) //List(Emp(1,james,12000.0), Emp(4,michael,11000.0), Emp(3,michael,12000.0), Emp(5,michael,15000.0), Emp(2,shaun,12000.0))
   
   
   //sort list of a tuple by their second element using sortBy
   val list_1 = List(('b',30),('c',10),('a',20))
   //println(list_1.sortBy(_._2)) // List((c,10), (a,20), (b,30))
   
   //similarly, we can  sort list of a tuple by their first element
   //println(list_1.sortBy(_._1)) //List((a,20), (b,30), (c,10))
   
   // sort in descending order on the basis of salary.
  //println(empList.sortWith(_.salary > _.salary)) //List(Emp(3,shaun,15000.0), Emp(1,james,13000.0), Emp(2,michael,12000.0))
  
   
   //we can also start using custom function (sortBySalary is custom function) 
   //ex empList.sortWith((emp1,emp2) => sortBySalary(emp1,emp2)) 
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   val students = List(("Mike", "2"), ("Peter", "8"), ("Pau", "7"))
   //println(students.maxBy( x => x._2)) //(Peter,8)
   //println(students.minBy( x => x._2)) //(Mike,2)
   
   //println(students.maxBy( x => x._1.length())) //(Peter,8)
   //println(students.minBy( x => x._1.length())) //(Pau,7)
   
   val oderListEx = students.maxBy( x => x._1.length() > 4)(Ordering[String].on(x => x.toString()))
   //println(oderListEx) //(Head ,1)
                       //(Peter,8)
   
   
   students.filter(x => x._1.length() > 3)
   students.filterNot(x => x._1.length() > 3)
   
   
   
   
   
}