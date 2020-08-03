class Field(private var height: Int, private var width: Int, private var countOfMines: Int) {

    private val minePos = getMinePos()
    private val arrayOfCell: Array<Array<Cell>> = Array(height) { Array(width) { Cell(1, 1) } }

    fun getCell(x: Int, y: Int): Cell {
        return arrayOfCell[y - 1][x - 1]
    }

    fun buildField() {
        for (i in arrayOfCell.indices)
            for (j in arrayOfCell[i].indices) {
                arrayOfCell[i][j] = NumberCell(j + 1, i + 1)
            }
        for (i in minePos.indices) {
            val posX = minePos[i][0]
            val posY = minePos[i][1]
            arrayOfCell[posY - 1][posX - 1] = MineCell(posX, posY)
        }
        minesAround()
    }

    fun printField() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                print(arrayOfCell[i][j].value)
            }
            println()
        }
    }

    private fun minesAround() {
        for (i in arrayOfCell.indices) {
            for (j in arrayOfCell[i].indices) {
                val cell = arrayOfCell[i][j]
                var count = 0
                if (cell is NumberCell) {
                    val x = j + 1
                    val y = i + 1
                    val leftMine = if (x - 1 == 0) '0' else arrayOfCell[i][j - 1]
                    val rightMine = if (x + 1 > width) '0' else arrayOfCell[i][j + 1]
                    val upperMine = if (y - 1 == 0) '0' else arrayOfCell[i - 1][j]
                    val downMine = if (y + 1 > height) '0' else arrayOfCell[i + 1][j]
                    val upperLeftMine = if (leftMine == '0' || upperMine == '0') '0' else arrayOfCell[i - 1][j - 1]
                    val upperRightMine = if (rightMine == '0' || upperMine == '0') '0' else arrayOfCell[i - 1][j + 1]
                    val downLeftMine = if (leftMine == '0' || downMine == '0') '0' else arrayOfCell[i + 1][j - 1]
                    val downRightMine = if (rightMine == '0' || downMine == '0') '0' else arrayOfCell[i + 1][j + 1]

                    count += if (leftMine is MineCell) 1 else 0
                    count += if (rightMine is MineCell) 1 else 0
                    count += if (upperMine is MineCell) 1 else 0
                    count += if (downMine is MineCell) 1 else 0
                    count += if (upperLeftMine is MineCell) 1 else 0
                    count += if (upperRightMine is MineCell) 1 else 0
                    count += if (downLeftMine is MineCell) 1 else 0
                    count += if (downRightMine is MineCell) 1 else 0

                    when (count) {
                        1 -> cell.value = '1'
                        2 -> cell.value = '2'
                        3 -> cell.value = '3'
                        4 -> cell.value = '4'
                        5 -> cell.value = '5'
                        6 -> cell.value = '6'
                        7 -> cell.value = '7'
                        8 -> cell.value = '8'
                    }
                }
            }
        }
    }

    private fun getMinePos(): Array<IntArray> {
        val array = Array(countOfMines) { IntArray(2) { 0 } }
        for (i in array.indices) {
            while (array[i][0] == 0 && array[i][1] == 0) {
                val randomX = (1..width).random()
                val randomY = (1..height).random()
                var count = 0
                for (k in array.indices) {
                    if (array[k][0] == randomX && array[k][1] == randomY) {
                        count += 1
                    }
                }
                if (count == 0) {
                    array[i][0] = randomX
                    array[i][1] = randomY
                }
            }
        }
        return array
    }
}