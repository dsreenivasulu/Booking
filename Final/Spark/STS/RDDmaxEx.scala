package com.operations.ex

import org.apache.spark.sql.SparkSession

object RDDmaxEx {
  
  def main(args : Array[String]) 
  {
    
    val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()
    
    val rdd = spark.sparkContext.parallelize(List(("4", "a", "b"), ("he", "c", "d"), ("3", "e", "f")))
    
    val frdd = rdd.max()
    
    println(frdd)
    
  }
  
}