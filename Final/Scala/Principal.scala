
abstract class Principal {

  def name : String
  
  def getName() : String  = 
  {
    return name
  }
}

case class Person(name : String) extends Principal 

case class Group(name : String) extends Principal

