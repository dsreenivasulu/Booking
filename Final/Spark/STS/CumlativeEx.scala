package com.text.cumaltivesum.ex

import org.apache.spark.sql.SparkSession

object CumlativeEx {
  
  def main(args : Array[String]) 
  {
    
    val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()
    
    import spark.implicits._
    
    val depDF = spark.read.csv("src/main/resources/dep.txt")
    
    
    println("show",  depDF.show())
    
    println("first", depDF.first())
    
    println("take", depDF.take(2))
    
    println("distnict", depDF.distinct())
    
    println(depDF.count())
    
    depDF.printSchema()
  }  
  
  case class Dept(ea_month : String, id : String, amount : String, year : String, circle_id : String)
}