package com.df.join.ex

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.Row

object JoinDFExamples extends App{
  
  val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()
  
  val emp = Seq((1,"ename1","1000"),(2,"ename2","2000"), (3,"ename3","3000"))
  
  val dept = Seq(("dname1", 1), ("dname2", 2), ("dname3", 3), ("dname5", 5))
  
  val empRDD = spark.sparkContext.parallelize(emp)
  
  val deptRDD = spark.sparkContext.parallelize(dept)
  
   val empDF = spark.createDataFrame(empRDD).toDF("empId", "empName", "salary")
   println(empDF.show())
   
   /**
    * +-----+-------+------+
      |empId|empName|salary|
      +-----+-------+------+
      |    1| ename1|  1000|
      |    2| ename2|  2000|
      |    3| ename3|  3000|
      +-----+-------+------+
    */
   
   
   import spark.implicits._
   val depDF = deptRDD.toDF("deptName", "empId")
   println(depDF.show())
   
   
   /**
    * +--------+-----+
      |deptName|empId|
      +--------+-----+
      |  dname1|    1|
      |  dname2|    2|
      |  dname3|    3|
      |  dname5|    5|
      +--------+-----+
    */
   
    
    //if you join on columns, you get duplicated columns.
    val joinedDF = empDF.join(depDF, empDF.col("empId") === depDF.col("empId"))
    println("joinedDF")
    println(joinedDF.show())
    
    /**
     *+-----+-------+------+--------+-----+
      |empId|empName|salary|deptName|empId|
      +-----+-------+------+--------+-----+
      |    1| ename1|  1000|  dname1|    1|
      |    3| ename3|  3000|  dname3|    3|
      |    2| ename2|  2000|  dname2|    2|
      +-----+-------+------+--------+-----+
     */
    
   
    val joinedDF2 = empDF.join(depDF, "empId")
     println("joinedDF2")
    println(joinedDF2.show())
  
    /**
     *|empId|empName|salary|deptName|
      +-----+-------+------+--------+
      |    1| ename1|  1000|  dname1|
      |    3| ename3|  3000|  dname3|
      |    2| ename2|  2000|  dname2|
      +-----+-------+------+--------+
     * 
     */
    
    val joinedDF3 = empDF.join(depDF, Seq("empId"))
    println("joinedDF3")
    println(joinedDF3.show())
  
    /**
     *|empId|empName|salary|deptName|
      +-----+-------+------+--------+
      |    1| ename1|  1000|  dname1|
      |    3| ename3|  3000|  dname3|
      |    2| ename2|  2000|  dname2|
      +-----+-------+------+--------+
     * 
     */
    
    //==========================================

    //Supported join types include: 'inner', 'outer', 'full', 'fullouter', 'leftouter', 'left', 'rightouter', 'right', 'leftsemi', 'leftanti', 'cross'.
    
    //Note :  1)outer == fullouter == full 
           // 2)left == leftouter 
           // 3)right == rightouter
           // 4)leftsemi
           // 5)leftanti
           // 6)cross
           // 7)inner 
    val newEmpRDD = spark.sparkContext.parallelize(Seq(Row(1, "ename1", 1000), Row(2, "ename2", 2000), Row(3, "ename3", 3000), Row(4, "ename4", 4000)))
    
    val empS = new StructType().add(StructField("empId", IntegerType, false))
                               .add(StructField("empName", StringType, false))
                               .add(StructField("salary", IntegerType, false))
    
    val newEmpDF = spark.createDataFrame(newEmpRDD, empS)
    println("newEmpDF struct type")
    println(newEmpDF.show())
    
    /**
     *+-----+-------+------+
      |empId|empName|salary|
      +-----+-------+------+
      |    1| ename1|  1000|
      |    2| ename2|  2000|
      |    3| ename3|  3000|
      |    4| ename4|  4000|
      +-----+-------+------+
     */
    
    val leftJoinDF = newEmpDF.join(depDF, newEmpDF.col("empId") === depDF.col("empId"), "left")
    println("leftJoinDF")
    println(leftJoinDF.show())
    /**
     *  +-----+-------+------+--------+-----+
        |empId|empName|salary|deptName|empId|
        +-----+-------+------+--------+-----+
        |    1| ename1|  1000|  dname1|    1|
        |    3| ename3|  3000|  dname3|    3|
        |    4| ename4|  4000|    null| null|
        |    2| ename2|  2000|  dname2|    2|
        +-----+-------+------+--------+-----+
     */
    
    
     val rightJoinDF = newEmpDF.join(depDF, newEmpDF.col("empId") === depDF.col("empId"), "right")
     println("rightJoinDF")
     println(rightJoinDF.show())
     /**
      * +-----+-------+------+--------+-----+
        |empId|empName|salary|deptName|empId|
        +-----+-------+------+--------+-----+
        |    1| ename1|  1000|  dname1|    1|
        |    3| ename3|  3000|  dname3|    3|
        | null|   null|  null|  dname5|    5|
        |    2| ename2|  2000|  dname2|    2|
        +-----+-------+------+--------+-----+
      * 
      */
     
    val outerJoinDF = newEmpDF.join(depDF, newEmpDF.col("empId") === depDF.col("empId"), "outer")
    println("outerJoinDF")
    println(outerJoinDF.show())
    /**
     *  +-----+-------+------+--------+-----+
        |empId|empName|salary|deptName|empId|
        +-----+-------+------+--------+-----+
        |    1| ename1|  1000|  dname1|    1|
        |    3| ename3|  3000|  dname3|    3|
        | null|   null|  null|  dname5|    5|
        |    4| ename4|  4000|    null| null|
        |    2| ename2|  2000|  dname2|    2|
        +-----+-------+------+--------+-----+
     */
    
    val fullouterJoinDF = newEmpDF.join(depDF, newEmpDF.col("empId") === depDF.col("empId"), "fullouter")
    println("fullouterJoinDF")
    println(fullouterJoinDF.show())
    /**
     *  +-----+-------+------+--------+-----+
        |empId|empName|salary|deptName|empId|
        +-----+-------+------+--------+-----+
        |    1| ename1|  1000|  dname1|    1|
        |    3| ename3|  3000|  dname3|    3|
        | null|   null|  null|  dname5|    5|
        |    4| ename4|  4000|    null| null|
        |    2| ename2|  2000|  dname2|    2|
        +-----+-------+------+--------+-----+
     */
    
    val leftouterJoinDF = newEmpDF.join(depDF, newEmpDF.col("empId") === depDF.col("empId"), "leftouter")
    println("leftouterJoinDF")
    println(leftouterJoinDF.show())
    /**
     *  +-----+-------+------+--------+-----+
        |empId|empName|salary|deptName|empId|
        +-----+-------+------+--------+-----+
        |    1| ename1|  1000|  dname1|    1|
        |    3| ename3|  3000|  dname3|    3|
        |    4| ename4|  4000|    null| null|
        |    2| ename2|  2000|  dname2|    2|
        +-----+-------+------+--------+-----+
     */
    
    val rightouterJoinDF = newEmpDF.join(depDF, newEmpDF.col("empId") === depDF.col("empId"), "rightouter")
    println("rightouterJoinDF")
    println(rightouterJoinDF.show())
    /**
     *+-----+-------+------+--------+-----+
      |empId|empName|salary|deptName|empId|
      +-----+-------+------+--------+-----+
      |    1| ename1|  1000|  dname1|    1|
      |    3| ename3|  3000|  dname3|    3|
      | null|   null|  null|  dname5|    5|
      |    2| ename2|  2000|  dname2|    2|
      +-----+-------+------+--------+-----+
     */
     
    val fullJoinDF = newEmpDF.join(depDF, newEmpDF.col("empId") === depDF.col("empId"), "full")
    println("fullJoinDF")
    println(fullJoinDF.show())
    
    /**
     *+-----+-------+------+--------+-----+
      |empId|empName|salary|deptName|empId|
      +-----+-------+------+--------+-----+
      |    1| ename1|  1000|  dname1|    1|
      |    3| ename3|  3000|  dname3|    3|
      | null|   null|  null|  dname5|    5|
      |    4| ename4|  4000|    null| null|
      |    2| ename2|  2000|  dname2|    2|
      +-----+-------+------+--------+-----+
     */
    
    val leftsemiJoinDF = newEmpDF.join(depDF, newEmpDF.col("empId") === depDF.col("empId"), "leftsemi")
    println("leftsemiJoinDF")
    println(leftsemiJoinDF.show())
    
    /**
     *  +-----+-------+------+
        |empId|empName|salary|
        +-----+-------+------+
        |    1| ename1|  1000|
        |    3| ename3|  3000|
        |    2| ename2|  2000|
        +-----+-------+------+
     */
    
    val leftantiJoinDF = newEmpDF.join(depDF, newEmpDF.col("empId") === depDF.col("empId"), "leftanti")
    println("leftantiJoinDF")
    println(leftantiJoinDF.show())
    
    /**
     * +-----+-------+------+
      |empId|empName|salary|
      +-----+-------+------+
      |    4| ename4|  4000|
      +-----+-------+------+
     */
    
    val crossJoinDF = newEmpDF.join(depDF, newEmpDF.col("empId") === depDF.col("empId"), "cross")
    println("crossJoinDF")
    println(crossJoinDF.show())
    
    /**
     *  +-----+-------+------+--------+-----+
        |empId|empName|salary|deptName|empId|
        +-----+-------+------+--------+-----+
        |    1| ename1|  1000|  dname1|    1|
        |    3| ename3|  3000|  dname3|    3|
        |    2| ename2|  2000|  dname2|    2|
        +-----+-------+------+--------+-----+	
     */
    
    
    val unMatchedLeftTable = newEmpDF.join(depDF, newEmpDF.col("empId") === depDF.col("empId"), "leftouter").where(depDF.col("empId").isNull)
    println("unMatchedLeftTable")
    println(unMatchedLeftTable.show())
}

