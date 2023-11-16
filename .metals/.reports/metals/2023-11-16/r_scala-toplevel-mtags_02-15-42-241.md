id: file://<WORKSPACE>/kojun/src/main/scala/Main.scala:[2022..2025) in Input.VirtualFile("file://<WORKSPACE>/kojun/src/main/scala/Main.scala", "object Kojun {
  
  type Board = Array[Array[Int]]
  type mergedBoard = Array[Array[(Int, Int)]]
  
  //retorna o tamanho da regiao da celula
  def regionSize(board: mergedBoard, row: Int, col: Int): Int = {
    board.flatten.count(cell => cell._2 == board(row)(col)._2)
  }

   // Função para verificar se um número esta ausente na regiao da celula selecionada
  def verifyRegion(num: Int, row: Int, col: Int, board: mergedBoard): Boolean = {
    // flatten a matriz board e filtra as células que estão na região alvo
    val cellsInRegion = board.flatten.filter(_._2 == board(row)(col)._2)
    // Conta a ocorrência do 'num' na região e verifica se ja esta na regiao, retorna true se num nao for um valor contido na regiao
    cellsInRegion.count(_._1 == num) == 0
  }

  def checkUpDown(num : Int, board : mergedBoard, row: Int, col : Int): Boolean = {
    val checkuP = if (row > 0) {
      if (board(row)(col)._2 == board(row - 1)(col)._2) {
        num < board(row-1)(col)._1
      } else {
        true
      }
    } else {
      true
    }
    val checkDown = if (row < board.length - 1) {
      // Check se a celula abaixo esta na mesma regiao 
      if (board(row)(col)._2 == board(row + 1)(col)._2) {
        // se esta na mesma regiao, checa se o valor da celula abaixo é menor que num
        num > board(row + 1)(col)._1
      } else {
        // regioes diferentes, regra nao se aplica
        true
      }
    } else {
      // linha mais abaixo, sem cel abaixo
      true
    }
    checkuP && checkDown
  }

  def checkOrthogonallyAdjacent(num : Int, board : Board, row : Int, col : Int): Boolean = {
    val lenBoard = board.length
    val notUp = if (row - 1 >= 0) board(row - 1)(col) != num else true

    val notDown = if (row + 1 < lenBoard ) board(row + 1)(col) != num else true

    val notLeft = if (col - 1 >= 0) board(row)(col - 1) != num else true

    val notRight = if (col + 1 < lenBoard) board(row)(col + 1) != num else true 

    notUp && notDown && notLeft && notRight
  }
  
  def 
  
  def mergeMatrices(board: Board, reg: Board): mergedBoard = {
    // Check if both matrices have the same dimensions
    require(board.length == reg.length && board(0).length == reg(0).length, "Matrices must be of the same size")

    // Merge the matrices into a matrix of tuples
    board.zipWithIndex.map { case (row, i) =>
      row.zipWithIndex.map { case (num, j) =>
        (num, reg(i)(j))
      }
    }
  }
  def printMergedMat(mergedmatrix : Kojun.mergedBoard ) : Unit = {
    mergedmatrix.foreach { row =>
      val rowString = row.map { case (num, region) => s"($num, $region)" }.mkString(" ")
      println(rowString)
    } 
  }
  def main(args:Array[String]): Unit = {
    val problem : Kojun.Board= 
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
    val regions : Kojun.Board = 
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
    
    // val x =Kojun.checkOrthogonallyAdjacent(3, problem, 0, 2) // false notDown == false
    // val x1 =Kojun.checkOrthogonallyAdjacent(3, problem, 1, 3) // false notLeft == false
    // val x2 =Kojun.checkOrthogonallyAdjacent(3, problem, 3, 1) // false notRight == false
    // val x3 =Kojun.checkOrthogonallyAdjacent(3, problem, 3, 5) // false notUp == false
    // println(x)
    // println(x1)
    // println(x2)
    // println(x3)
    val mergedMatrix : Kojun.mergedBoard= Kojun.mergeMatrices(problem, regions)
        // Test Cases for checkUp
    // println(s"Test 1 (Top Row): ${Kojun.checkUpDown(2, mergedMatrix, 0, 1)}")
    // println(s"Test 2 (Bottom Row): ${Kojun.checkUpDown(2, mergedMatrix, mergedMatrix.length - 1, 1)}")
    // println(s"Test 3 (Correctly Positioned): ${Kojun.checkUpDown(2, mergedMatrix, 1, 1)}")
    // println(s"Test 4 (Incorrectly Positioned): ${Kojun.checkUpDown(4, mergedMatrix, 2, 1)}")
    // println(s"Test 5 (Different Regions): ${Kojun.checkUpDown(3, mergedMatrix, 2, 1)}")
    // val testRow = 2
    // val testCol = 1
    // println(s"Cell Above: ${mergedMatrix(testRow - 1)(testCol)}")
    // println(s"Current Cell: ${mergedMatrix(testRow)(testCol)}")
    // println(s"Cell Below: ${mergedMatrix(testRow + 1)(testCol)}")

    // println(s"Test 4 (Correctly Positioned): ${Kojun.checkUpDown(4, mergedMatrix, testRow, testCol)}")
    println(regionSize(mergedMatrix,2,0))

    Kojun.printMergedMat(mergedMatrix)
    }  
}
")
file://<WORKSPACE>/kojun/src/main/scala/Main.scala
file://<WORKSPACE>/kojun/src/main/scala/Main.scala:60: error: expected identifier; obtained def
  def mergeMatrices(board: Board, reg: Board): mergedBoard = {
  ^
#### Short summary: 

expected identifier; obtained def