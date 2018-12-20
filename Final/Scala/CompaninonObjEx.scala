import CompaninonObjEx.compc


object CompaninonObjEx {
  
  class compc(nameCombined : String) 
  {
    private var nameC = ""
  }
  
  object compc 
  {
    def apply(name : String) 
    {
      val c = new compc(name)
      c.nameC = name
      println(c.nameC)
      c
    }
  }
  
  def main(args : Array[String]) 
  {
    compc.apply("HEllo")
    
  }
  
}