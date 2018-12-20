package com.operations.ex

import org.apache.spark.sql.SparkSession

object CountByKeyEx {
  
  def main(args : Array[String])
  {
    val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()
    
    val rdd = spark.sparkContext.parallelize(List((1, "Hi"), (2, "Hi"), (1, "Hello")))
    
    val map = rdd.countByKey()
    
    println("ex1")
    map.foreach(println)
    //o/p  (1,2) 
    //     (2,1)

    //Note : countByKey() will only works on Rdd key value pair (two elements tuple)
    
  }
  
  
}