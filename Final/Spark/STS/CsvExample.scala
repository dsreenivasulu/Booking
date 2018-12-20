package com.csv.ex

import org.apache.spark.sql.SparkSession

object CsvExample {
  
  def main(args : Array[String]) 
  {
    
    val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()
    
    val df = spark.read.csv("src/main/resources/sales.csv")
    
    df.printSchema()
    
    val first = df.select("_c0")
    
    df.createTempView("sales")

    //interacting with catalogue

    val catalog = spark.catalog

    //print the databases

    catalog.listDatabases().select("name").show()

    // print all the tables

    catalog.listTables().select("name").show()

    // is cached
    println(catalog.isCached("sales"))
    df.cache()
    println(catalog.isCached("sales"))

    // drop the table
    catalog.dropTempView("sales")
    catalog.listTables().select("name").show()

    // list functions
catalog.listFunctions().select("name","description","className","isTemporary").show(100)
    
    
  }
  
}