import scala.util.control.Breaks._
import scala.collection.mutable
object Kojun {
  //auxiliares para declaracao de tipo dos tabuleiros
  type Board = Array[Array[Int]]
  type mergedBoard = Array[Array[(Int, Int)]]
  
  //retorna o tamanho da regiao da celula
  def regionSize(board: mergedBoard, row: Int, col: Int): Int = {
    board.flatten.count(cell => cell._2 == board(row)(col)._2)
  }

  def solve(board: mergedBoard, currentRow: Int = 0, currentCol: Int = 0): Option[mergedBoard] = {
  // Se currentRow é igual ao comprimento do tabuleiro, todas as células foram preenchidas
  if (currentRow == board.length) {
    Some(board) // retorna o tabuleiro resolvido
  } else {
    val (nextRow, nextCol) = if (currentCol == board(currentRow).length - 1) { 
      (currentRow + 1, 0) // se for o fim da linha, vai para o inicio da proxima linha
    } else { // senao, vai para a proxima coluna da linha
      (currentRow, currentCol + 1)
    }
    // Se a cell atual já estiver preenchida (diferente de 0), passa para a próxima cell
    if (board(currentRow)(currentCol)._1 != 0) {
      solve(board, nextRow, nextCol)
    } else {
      // Se a cell atual for 0, tenta encontrar um numero valido 
      var result: Option[mergedBoard] = None
      breakable {
        // loop que passa por todos os numeros possiveis para region, de 1 a N, N igual a quantidaded celulas na region
        for (num <- 1 to regionSize(board, currentRow, currentCol)) {
          if (verifyPossibleNumber(num, board, currentRow, currentCol)) { // se for um numero possivel, define o numero na celula 
            board(currentRow)(currentCol) = (num, board(currentRow)(currentCol)._2)
            // chama o solve para a proxima celula
            solve(board, nextRow, nextCol) match {
              case Some(solvedBoard) => 
                result = Some(solvedBoard) // caso achou solução, guarda o resultado e sai do loop
                break
              case None => 
                board(currentRow)(currentCol) = (0, board(currentRow)(currentCol)._2) // Backtrack, reseta a celula e tenta outro numero
            }
          }
        }
      }
      result
    }
  }
  }
   // Função para verificar se um número ja existe na regiao da celula selecionada
  def verifyRegion(num: Int,  board: mergedBoard, row: Int, col: Int): Boolean = {
    // flatten a matriz board e filtra as células que estão na região alvo
    val cellsInRegion = board.flatten.filter(_._2 == board(row)(col)._2)
    // Conta a ocorrência do 'num' na região e verifica se ja esta na regiao, retorna true se num nao for um valor contido na regiao
    cellsInRegion.count(_._1 == num) == 0
  }

  def checkUpDown(num : Int, board : mergedBoard, row: Int, col : Int): Boolean = {
    val checkuP = if (row > 0) {
      // Check se a celula acima esta na mesma regiao 
      if (board(row)(col)._2 == board(row - 1)(col)._2) {
        // se esta na mesma regiao, checa se o valor da celula acima é maior que num
        num < board(row-1)(col)._1
      } else {
        // regioes diferentes, regra nao se aplica
        true
      }
    } else {
      // linha mais acima, sem cel acima
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
    // retorna true se as duas validacoes passarem
    checkuP && checkDown 
  }

  def checkOrthogonallyAdjacent(num : Int, board : mergedBoard, row : Int, col : Int): Boolean = {
    val lenBoard = board.length

    // Verifica se o número é diferente do número acima (se existir)
    val notUp = if (row - 1 >= 0) board(row - 1)(col)._1 != num else true
    // Verifica se o número é diferente do número abaixo (se existir)
    val notDown = if (row + 1 < lenBoard ) board(row + 1)(col)._1 != num else true
    // Verifica se o número é diferente do número a esquerda (se existir)
    val notLeft = if (col - 1 >= 0) board(row)(col - 1)._1 != num else true
    // Verifica se o número é diferente do número a direita (se existir)
    val notRight = if (col + 1 < lenBoard) board(row)(col + 1)._1 != num else true 

    // Retorna verdadeiro se todas as verificacoes forem verdadeiras (não ha numeros iguais adjacentes ortogonalmente)
    notUp && notDown && notLeft && notRight
  } 
  
  def verifyPossibleNumber(num : Int, board : mergedBoard, row : Int, col : Int): Boolean = {
    // verdadeiro se as tres validacoes abaixo forem verdadeiras
    checkOrthogonallyAdjacent(num, board, row, col) && checkUpDown(num, board, row, col) && verifyRegion(num, board, row, col)
  }
  
  def mergeMatrices(board: Board, reg: Board): mergedBoard = {
    // verifica se as 2 matrizes tem as mesmas dimensoes, joga uma excessao se as dimensoes forem diferentes
    require(board.length == reg.length && board(0).length == reg(0).length, "Matrices must be of the same size")

    // mescla as matrizes em uma matriz de tuplas
    board.zipWithIndex.map { case (row, i) =>
      row.zipWithIndex.map { case (num, j) =>
        (num, reg(i)(j))
      }
    }
  }

  def printMergedMat(mergedmatrix: Kojun.mergedBoard): Unit = {
    mergedmatrix.foreach { row =>
      // Formata cada tupla da linha. Se a regiao for menor que 10, adiciona um zero a esquerda
      val rowString = row.map { 
        case (num, region) => s"($num, ${f"$region%02d"})" 
      }.mkString(" ") // Junta as tuplas da liha com um espaco entre elas
      println(rowString)
      println() // print de linha vazia apos cada linha, para melhorar o visual do tabuleiro
    }
  }


  def main(args:Array[String]): Unit = {
    val problem4x4 :Kojun.Board = 
      Array (
        Array(0,0,0,0),
        Array(0,0,0,0),
        Array(0,0,0,0),
        Array(0,0,0,0),
      )
    val regions4x4 :Kojun.Board = 
      Array (
        Array(0,0,2,2),
        Array(1,1,4,4),
        Array(3,3,4,5),
        Array(5,5,5,5),
      )
    val mergedMatrix4x4 : Kojun.mergedBoard = Kojun.mergeMatrices(problem4x4, regions4x4)
    
    // exemplo da pagina inicial https://www.janko.at/Raetsel/Kojun/index.htm
    val problem8x8 : Kojun.Board= 
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
    val regions8x8 : Kojun.Board = 
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
    val mergedMatrix8x8 : Kojun.mergedBoard= Kojun.mergeMatrices(problem8x8, regions8x8)
    // https://www.janko.at/Raetsel/Kojun/010.a.htm 
    val problem10x10 : Kojun.Board=
       Array(
        Array(5,0,2,0,2,0,3,1,3,1),
        Array(0,4,0,1,0,5,0,5,0,4),
        Array(7,5,1,7,0,0,3,1,3,0),
        Array(0,4,0,0,0,0,0,0,0,3),
        Array(2,0,3,4,0,2,0,0,4,0),
        Array(5,0,2,0,6,0,0,0,0,0),
        Array(0,1,3,0,1,0,0,4,0,3),
        Array(6,7,0,3,0,1,4,0,0,1),
        Array(4,0,3,0,4,0,0,0,0,3),
        Array(0,1,0,2,0,6,2,0,2,1),
      )

      val regions10x10 : Kojun.Board=
       Array(
        Array(0,1,1,1,2,2,2,2,3,3),
        Array(0,0,0,1,4,4,5,5,3,5),
        Array(6,6,0,4,4,7,8,5,5,5),
        Array(6,6,4,4,9,7,8,8,8,10),
        Array(6,6,6,4,9,9,11,10,10,10),
        Array(12,12,13,13,13,9,15,15,16,16),
        Array(12,12,12,13,13,14,17,18,16,16),
        Array(20,20,12,13,14,14,17,18,19,19),
        Array(20,20,21,21,21,21,17,18,18,22),
        Array(20,20,20,21,21,21,17,17,22,22),
      )
    val mergedMatrix10x10 : Kojun.mergedBoard= Kojun.mergeMatrices(problem10x10, regions10x10)
    
    println("Board4x4")
    val result4x4 = solve(mergedMatrix4x4)
    result4x4 match {
      case Some(board) => Kojun.printMergedMat(board)
      case None => println("no solution")
    }

    println("Board8x8")
    val result8x8 = solve(mergedMatrix8x8)
    result8x8 match {
      case Some(board) => Kojun.printMergedMat(board) // se houver solucao, print board
      case None => println("no solution")
    }

    println("Board10x10")
    val result10x10 = solve(mergedMatrix10x10)
    result10x10 match {
      case Some(board) => Kojun.printMergedMat(board) // se houver solucao, print board
      case None => println("no solution")
    }
    }  
}