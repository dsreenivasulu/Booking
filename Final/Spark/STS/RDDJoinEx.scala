package com.wc.ex

import org.apache.spark.sql.SparkSession

object RDDJoinEx {
  
  def main(args : Array[String]) 
  {
    val sparkSession = SparkSession.builder().config("spark.master", "local").getOrCreate()
  
    
      val emp = Seq((2, "sreeni", 1), (3, "vasu", 1))
      val dep = Seq((1, "dev", 2), (2, "test", 3))
      
      val empRDD = sparkSession.sparkContext.parallelize(emp)
      val depRDD = sparkSession.sparkContext.parallelize(dep)
      
      val keyValuePairEmpRDD  = empRDD.map(x => ((x._3), x))
      
      val keyValuePairDepRDD = depRDD.map( x => (x._1, x))

      println("collecting "+keyValuePairEmpRDD.collect())
      
      val join = keyValuePairEmpRDD.fullOuterJoin(keyValuePairDepRDD)
      
      join.foreach( f => 
      {
         println(f)
      })
  }
  
  
}