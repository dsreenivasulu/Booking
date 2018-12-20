package com.operations.ex

import org.apache.spark.sql.SparkSession

object zipWithIndexEx {

  def main(args: Array[String]) {
    
    val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()

    val depDF = spark.read.csv("src/main/resources/dep.txt")

    val zrdd = depDF.rdd.zipWithIndex()

    zrdd.foreach(println)

    val arrayRDD = zrdd.map(line => (line._1.mkString(","), line._2))

    arrayRDD.foreach(println)

    val splittedWords = arrayRDD.map(eachWord => (eachWord._1.split(","), eachWord._2))

    splittedWords.foreach(println)

    val finalRDD = splittedWords.filter(word => word._1.contains("April"))

    finalRDD.foreach(line => println(line._1(0).toString(), line._2))
    
    
    
    //=======zip example
    val rdd1 = spark.sparkContext.parallelize(List("Hi", "Hello", "Hi", "bye"), 2)
    

    val rdd2 = spark.sparkContext.parallelize(List("HI", "HELLO", "HI", "BYE"), 2)
    
    
    val rdd3 = rdd1.zip(rdd2)
    println("zip example")
    rdd3.foreach(println)
    
    val rdd4 = rdd1.zipWithIndex()
    println("zipWithIndex example")
    rdd4.foreach(println)
    
    val rdd5 = rdd1.zipWithUniqueId()
    println("zipWithUniqueId example")
    rdd5.foreach(println)
    
    val a = Array(1,2,3,4,5,6,7,8,9)
    val m1 = spark.sparkContext.parallelize(a,3)
    
    val rdd6 = m1.zipWithIndex()
    println("zipWithIndex example")
    rdd6.foreach(println)
    
    val rdd7 = m1.zipWithUniqueId()
    println("zipWithUniqueId example")
    rdd7.foreach(println)
    
  }
}