package com.operations.ex

import org.apache.spark.sql.SparkSession

object AggregrateEx {
  
  def main(args : Array[String]) 
  {
    
    val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()
    
    val rdd = spark.sparkContext.parallelize(List(1, 2, 3, 4), 2)
    
    val ex1 = rdd.aggregate(0, 0 )(
        (acc, value) => (acc._1 + value, acc._2 + 1), 
        (acc1, acc2) => (acc1._1 + acc2._1, acc1._2 + acc2._2)
        )
    
    println(s"example $ex1")  
    /**
     * explanation 
     *   part-1                                part-2  
     *   1,2                                    3,4
     *   
     *   sequence operation
     *   
     *   acc = 0, value =0                      acc = 0, value =0 
     *                  
     *   value = 1                                value = 3
     *   acc = 0+1 = 1                            acc = 0+1 = 1 
     *   value = 0+1 = 2                          value = 0+3 = 3
     *      acc = 1, value =2                       acc = 1, value = 3
     *   
     *   value = 2                                value = 2
     *    acc = 1+1 = 2                             acc = 1+1 = 2 
     *   value = 1+2 = 3                            value = 3+4 = 7
     *      acc = 2, value =3
     *                                   
     *       (3, 2)                                   (7, 2)
     *       
     *       
     *    combine operation 
     *           3 + 7 = 10
     *           2 + 2 =  4 
     *   
     *     o/p is (10, 4)
     *    
     */
    
    
     val ex2 = rdd.aggregate(0)(
        (x, y) => Math.max(x, y), 
        (x, y) => x+y 
        )
        
    println(s"example2 $ex2")  
        /**
         * 0, 1, 2        0, 3, 4 
         * 
         * 2            4 
         *  
         *  o/p is 6 
         *   
         */
        
       val ex3 = rdd.aggregate(0)(
        (x, y) => Math.min(x, y), 
        (x, y) => x+y 
        )
        
    println(s"example3 $ex3")  
        /**
         * 0, 1, 2        0, 3, 4 
         * 
         * 0                0          
         *  
         *  o/p is 0
         *   
         */   
     
        
        val ex4 = rdd.aggregate(0)(
        (x, y) => x + y, 
        (x, y) => x + y 
        )
    
         println(s"example4 $ex4")  
        /**
         * 0, 1, 2        0, 3, 4 
         * 
         * 0+1 =  1        0+3 = 3
         * 1+2  = 3        3+4 = 7 
         *    3               7            
         *  
         *  o/p is 10
         *   
         */   
        
         val complexRdd = spark.sparkContext.parallelize(List("Hi", "Hello", "Yo", "Bye"), 2)
         
          val cex1 = complexRdd.aggregate(0, 0 )(
        (acc, value) => (acc._1 + value.toInt, acc._2 + 1), 
        (acc1, acc2) => (acc1._1 + acc2._1, acc1._2 + acc2._2)
        )
         
          println(s"complexRdd example $cex1")  
    /**
     * explanation 
     *   part-1                                part-2  
     *   1,2                                    3,4
     *   
     *   sequence operation
     *   
     *   acc = 0, value =0                      acc = 0, value =0 
     *                  
     *   value = 1                                value = 3
     *   acc = 0+1 = 1                            acc = 0+1 = 1 
     *   value = 0+1 = 2                          value = 0+3 = 3
     *      acc = 1, value =2                       acc = 1, value = 3
     *   
     *   value = 2                                value = 2
     *    acc = 1+1 = 2                             acc = 1+1 = 2 
     *   value = 1+2 = 3                            value = 3+4 = 7
     *      acc = 2, value =3
     *                                   
     *       (3, 2)                                   (7, 2)
     *       
     *       
     *    combine operation 
     *           3 + 7 = 10
     *           2 + 2 =  4 
     *   
     *     o/p is (10, 4)
     *    
     */
    
    
     val ex5 = rdd.aggregate(0)(
        (x, y) => Math.max(x, y), 
        (x, y) => x+y 
        )
        
    println(s"example2 $ex2")  
        /**
         * 0, 1, 2        0, 3, 4 
         * 
         * 2            4 
         *  
         *  o/p is 6 
         *   
         */
        
       val ex6 = rdd.aggregate(0)(
        (x, y) => Math.min(x, y), 
        (x, y) => x+y 
        )
        
    println(s"example3 $ex3")  
        /**
         * 0, 1, 2        0, 3, 4 
         * 
         * 0                0          
         *  
         *  o/p is 0
         *   
         */   
     
        
        val ex7 = rdd.aggregate(0)(
        (x, y) => x + y, 
        (x, y) => x + y 
        )
    
         println(s"example4 $ex4")  
        /**
         * 0, 1, 2        0, 3, 4 
         * 
         * 0+1 =  1        0+3 = 3
         * 1+2  = 3        3+4 = 7 
         *    3               7            
         *  
         *  o/p is 10
         *   
         */   
         
        
  }
  
}