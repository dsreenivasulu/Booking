package com.operations.ex

import org.apache.spark.sql.SparkSession

object collectAsMapExample {
  
  def main(args : Array[String]) 
  {
    
    val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()
    
    val rdd = spark.sparkContext.parallelize(Array[(String, Int)](("A", 1), ("B", 2), ("B", 3), ("C", 4), ("C", 5), ("C", 6)))
    
    println(rdd.collectAsMap()) //o/p is Map(A -> 1, C -> 6, B -> 3)
    
    
    
    val rdd2 = spark.sparkContext.parallelize(List(1,2,3))
    
    val rdd3 = rdd2.zip(rdd2) //o/p is (1,1)
//(2,2)
//(3,3)
    
    rdd3.foreach(println)
    
    println(rdd3.collectAsMap()) //o/p is Map(2 -> 2, 1 -> 1, 3 -> 3)
    
    
    
  }
  
  
  
  
}