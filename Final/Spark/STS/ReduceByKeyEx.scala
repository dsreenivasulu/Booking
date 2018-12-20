package com.operations.ex

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object ReduceByKeyEx {
  def main(args : Array[String]) 
  {
    
    val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()
    
      val pureRDD = spark.sparkContext.parallelize(List( ("dep1", "emp1", 100),
    ("dep1", "emp2", 1000),
    ("dep3", "emp3", 300),
    ("dep3", "emp4", 3000),
    ("dep2", "emp5", 200),
    ("dep2", "emp6", 2000),
    ("dep4", "emp7", 400)
    ))
            
    
            val mapRDD = pureRDD.map(x => (x._1.toString, x._3.toInt) )

            val maxSal = mapRDD.reduceByKey((x,y) => math.max(x, y))
            
            val minSal = mapRDD.reduceByKey((x,y) => math.min(x, y))
            
            val sumSal = mapRDD.reduceByKey((x,y) => x + y)
    
    println("maxSal")
    maxSal.foreach(println)
     println("minSal")
    minSal.foreach(println)
     println("sumSal")
    sumSal.foreach(println)
    
    
    val joinRDD = pureRDD.map( x => (x._1, x) )
    val maxJoinRDD = maxSal.map( x => (x._1, x) )
    
     println("join")
     joinRDD.leftOuterJoin(maxJoinRDD).foreach(println)
    
    
    val rdd = spark.sparkContext.parallelize(List( ("dep1", "emp1", "100"),
    ("dep1", "emp2", "1000"),
    ("dep3", "emp3", "300"),
    ("dep3", "emp4", "3000"),
    ("dep2", "emp5", "200"),
    ("dep2", "emp6", "2000"),
    ("dep4", "emp7", "400")
    ))
    
    //Find the max sal in each deparament 
    
   import spark.implicits._
   
   val df = rdd.toDF("dep", "emp", "sal")
   
   val w = Window.partitionBy($"dep").orderBy($"sal".desc) 
    
   
   val fdf = df.withColumn("maxS", row_number.over(w)).where($"maxS" === 1).drop("maxS")
   println("df")
   
   println(fdf.show())
    
    
    val gdf = df.groupBy($"dep").agg(max($"sal").alias("maxSal"), min($"sal"), sum($"sal"), avg($"sal"), sumDistinct($"sal"),count($"sal"))
    
    println("gdf.show()")
    println(gdf.show())
  }
}