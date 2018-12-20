package com.operations.ex

import org.apache.spark.sql.SparkSession

object SortByEx {
  
  def main(args : Array[String]) 
  {
    val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()
    
    val rdd = spark.sparkContext.parallelize(Array(2, 1, 3))
    
    val sortedRDD = rdd.sortBy(x => x)
    
    import scala.runtime.ScalaRunTime._
    
    val string = stringOf(sortedRDD.collect())
        
    println(s"default is ascending order = $string") // out put is Array(1, 2, 3)
    
    val descSortedRDD = rdd.sortBy(x => x, false)
    
    val dstring = descSortedRDD.collect().mkString(",")
    println(s"descending order is = $dstring") // out put is Array(3, 2, 1)
    
    
    val lines = spark.sparkContext.textFile("src/main/resources/dep.txt")
    lines.map( x => x)
    
    
    //http://homepage.cs.latrobe.edu.au/zhe/ZhenHeSparkRDDAPIExamples.html#sortBy
  }
}