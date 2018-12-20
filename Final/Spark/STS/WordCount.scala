package com.wc.ex

import org.apache.spark.sql.SparkSession

object WordCount {
  

 
  
  def main(args : Array[String]) 
  {
     val spark  = SparkSession.builder().config("spark.master", "local").enableHiveSupport().getOrCreate()
  

  val rddLines = spark.sparkContext.textFile("src/main/resources/dep.txt")
  
  val splittedRDDLines = rddLines.flatMap(line => line.split(","))
  
  val mapRddWorkds = splittedRDDLines.map(word => (word, 1))
  
  val reduced = mapRddWorkds.reduceByKey((x, y) => x+ y)
  
  println("reduced "+reduced.foreach(println))
  
  val max = reduced.takeOrdered(1)(Ordering[Int].reverse.on(_._2))
  
  println("max "+max.foreach(println))
  
 // spark.sq
  
  
  println("reduced "+reduced.count())
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}