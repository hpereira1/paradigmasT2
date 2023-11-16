file://<WORKSPACE>/kojun/src/main/scala/Main.scala
### file%3A%2F%2F%2Fhome%2Fhpereira1%2FDocuments%2FparadigmasT2%2Fkojun%2Fsrc%2Fmain%2Fscala%2FMain.scala:73: error: end of file expected but } found
}
^

occurred in the presentation compiler.

action parameters:
uri: file://<WORKSPACE>/kojun/src/main/scala/Main.scala
text:
```scala
object Kojun {
  
  type Board = Array[Array[Int]]
  type mergedBoard = Array[Array[(Int, Int)]]


  def checkOrthogonallyAdjacent(num : Int, board : Board, row : Int, col : Int): Boolean = {
    val lenBoard = board.length
    val notUp = if (row - 1 >= 0) board(row - 1)(col) != num else true

    val notDown = if (row + 1 < lenBoard ) board(row + 1)(col) != num else true

    val notLeft = if (col - 1 >= 0) board(row)(col - 1) != num else true

    val notRight = if (col + 1 < lenBoard) board(row)(col + 1) != num else true 

    notUp && notDown && notLeft && notRight
  }
  
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
      
    val x =Kojun.checkOrthogonallyAdjacent(3, problem, 0, 2) // false notDown == false
    val x1 =Kojun.checkOrthogonallyAdjacent(3, problem, 1, 3) // false notLeft == false
    val x2 =Kojun.checkOrthogonallyAdjacent(3, problem, 3, 1) // false notRight == false
    val x3 =Kojun.checkOrthogonallyAdjacent(3, problem, 3, 5) // false notUp == false
    println(x)
    println(x1)
    println(x2)
    println(x3)
    val mergedMatrix : Kojun.mergedBoard= Kojun.mergeMatrices(problem, regions)
    
    }  
}
}
```



#### Error stacktrace:

```
scala.meta.internal.parsers.Reporter.syntaxError(Reporter.scala:16)
	scala.meta.internal.parsers.Reporter.syntaxError$(Reporter.scala:16)
	scala.meta.internal.parsers.Reporter$$anon$1.syntaxError(Reporter.scala:22)
	scala.meta.internal.parsers.Reporter.syntaxError(Reporter.scala:17)
	scala.meta.internal.parsers.Reporter.syntaxError$(Reporter.scala:17)
	scala.meta.internal.parsers.Reporter$$anon$1.syntaxError(Reporter.scala:22)
	scala.meta.internal.parsers.ScalametaParser.syntaxErrorExpected(ScalametaParser.scala:421)
	scala.meta.internal.parsers.ScalametaParser.expect(ScalametaParser.scala:423)
	scala.meta.internal.parsers.ScalametaParser.accept(ScalametaParser.scala:427)
	scala.meta.internal.parsers.ScalametaParser.parseRuleAfterBOF(ScalametaParser.scala:63)
	scala.meta.internal.parsers.ScalametaParser.parseRule(ScalametaParser.scala:54)
	scala.meta.internal.parsers.ScalametaParser.parseSource(ScalametaParser.scala:132)
	scala.meta.parsers.Parse$.$anonfun$parseSource$1(Parse.scala:29)
	scala.meta.parsers.Parse$$anon$1.apply(Parse.scala:36)
	scala.meta.parsers.Api$XtensionParseDialectInput.parse(Api.scala:25)
	scala.meta.internal.semanticdb.scalac.ParseOps$XtensionCompilationUnitSource.toSource(ParseOps.scala:17)
	scala.meta.internal.semanticdb.scalac.TextDocumentOps$XtensionCompilationUnitDocument.toTextDocument(TextDocumentOps.scala:206)
	scala.meta.internal.pc.SemanticdbTextDocumentProvider.textDocument(SemanticdbTextDocumentProvider.scala:54)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$semanticdbTextDocument$1(ScalaPresentationCompiler.scala:356)
```
#### Short summary: 

file%3A%2F%2F%2Fhome%2Fhpereira1%2FDocuments%2FparadigmasT2%2Fkojun%2Fsrc%2Fmain%2Fscala%2FMain.scala:73: error: end of file expected but } found
}
^