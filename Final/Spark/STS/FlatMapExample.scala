package com.operations.ex

import org.apache.spark.sql.SparkSession

object FlatMapExample {
  def main(args: Array[String]) {
    
   val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()
   
   val data = spark.sparkContext.parallelize(Array[(String, Int)](("A", 1), ("B", 2),
		    						 ("B", 3), ("C", 4),
		    						 ("C", 5), ("C", 6)))    		
		    						 

		    			val results = data.flatMap(t => (t._1 + t._2))			 
		    						 
             		    results.foreach(println)				 //A
//1
//B
//2
//B
//3
//C
//4
//C
//5
//C
//6		 
  }
	

}