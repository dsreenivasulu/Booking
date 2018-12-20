package com.operations.ex

import org.apache.spark.sql.SparkSession

object FoldEx {
  
  def main(args : Array[String]) 
  {
    val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()
    
    val rdd  = spark.sparkContext.parallelize(1 to 6, 3)
    
    //1, 2 => 5+1+2 = 8
    //3, 4 => 5+3+4 = 12
    //5, 6 => 5+5+6 = 16
    
    //5 + 8 + 12 + 16 = 41
    val op = rdd.fold(5)((x, y) => x + y)
    
    println(op)
    
    val rdd1  = spark.sparkContext.parallelize(1 to 6, 2)
    
    //1, 2, 3 => 5+1+2+3 = 11
    //4, 5, 6 => 5+4+5+6 = 20
    
    //5 + 11 + 20 = 36
    val op1 = rdd1.fold(5)((x, y) => x + y)
    
    println(op1)
    
  }
}