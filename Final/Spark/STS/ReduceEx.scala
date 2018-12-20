package com.operations.ex

import org.apache.spark.sql.SparkSession

object ReduceEx {
 
  def main(args : Array[String]) 
  {
    
    val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()
    
    val rdd = spark.sparkContext.parallelize(1 to 6, 3)
    
    //1,2 = 3
    //3,4  = 7
    //5,6 =  11
    
    //3+7+11 = 21
    val result = rdd.reduce((x, y) => x + y)
    
    println(result)
  }
}