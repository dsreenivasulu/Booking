package com.operations.ex

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object DataFrameGroupByAggregationEx extends App
{
 
   val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()
    
    import spark.implicits._
    
    
    val depDF = spark.read.csv("src/main/resources/sales.csv")
    println(depDF.printSchema())
    
    val newDF = depDF.toDF("transactionId", "customerId","itemId", "amountPaid");
    
   val gdf = newDF.groupBy("customerId").agg(sum("amountPaid"))
   
   
   println(gdf.show())
    
   
    
}