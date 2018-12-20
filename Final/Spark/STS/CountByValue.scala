package com.operations.ex

import org.apache.spark.sql.SparkSession

object CountByValue {
  def main(args : Array[String]) 
  {
    val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()

    val rdd = spark.sparkContext.parallelize(List(1,2,1,1,1, 2,3,4,5,5,5, 6))

    val res = rdd.countByValue()

    println("res ex")
    res.foreach(println)
    /**
     * o/p is  
     *(5,3)
			(1,4)
			(6,1)
			(2,2)
			(3,1)
			(4,1)
     */

   
   /* @note This method should only be used if the resulting map is expected to be small, as
   * the whole thing is loaded into the driver's memory.
   * To handle very large results, consider using
   *
   * {{{
   * rdd.map(x => (x, 1L)).reduceByKey(_ + _)
   * }}}
   * 
   */

    val countVa = rdd.map(x => (x, 1) ).reduceByKey((x, y ) => (x + y) )

    println("countVa ex")
    countVa.foreach(println)
   /**
     * o/p is  
     *(5,3)
			(1,4)
			(6,1)
			(2,2)
			(3,1)
			(4,1)
     */
    
     
     

  }
}