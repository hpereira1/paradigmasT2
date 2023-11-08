object Kojun {
  
  type Board = Array[Array[Int]]
  


  def checkOrthogonallyAdjacent(num : Int, board : Board, row : Int, col : Int): Boolean = {
    val lenBoard = board.length
    val notUp = if (row - 1 >= 0) board(row - 1)(col) != num else true

    val notDown = if (row + 1 < lenBoard ) board(row + 1)(col) != num else true

    val notLeft = if (col - 1 >= 0) board(row)(col - 1) != num else true

    val notRight = if (col + 1 < lenBoard) board(row)(col + 1) != num else true 

    notUp && notDown && notLeft && notRight
  }


  def main(args:Array[String]): Unit = {
    val problem = 
      Array(
        Array(0,0,0,0,0,0,0,0),
        Array(0,1,3,0,0,0,0,0),
        Array(0,0,0,0,0,3,0,0),
        Array(0,0,3,0,0,0,0,0),
        Array(0,5,0,3,0,0,0,0),
        Array(0,2,0,0,0,0,0,0),
        Array(0,0,0,0,0,0,3,0),
        Array(0,0,5,3,0,0,0,0),
      )
    val regions = 
      Array(
        Array(0,0,1,1,2,3,4,4),
        Array(0,0,5,1,7,3,3,4),
        Array(5,5,5,6,7,8,9,9),
        Array(10,10,10,6,7,8,8,9),
        Array(11,6,6,6,6,8,8,9),
        Array(11,12,13,13,13,14,15,9),
        Array(12,12,12,12,17,14,14,14),
        Array(16,17,17,17,17,14,18,18),
      )
      
      val x =checkOrthogonallyAdjacent(3, problem, 0, 2) // false notDown == false
      val x1 =checkOrthogonallyAdjacent(3, problem, 1, 3) // false notLeft == false
      val x2 =checkOrthogonallyAdjacent(3, problem, 3, 1) // false notRight == false
      val x3 =checkOrthogonallyAdjacent(3, problem, 3, 5) // false notUp == false
      println(x)
      println(x1)
      println(x2)
      println(x3)
  }
}
