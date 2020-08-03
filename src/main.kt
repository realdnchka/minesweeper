import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    print("Fill number of mines > ")
    val countOfMines = scanner.nextInt()
    print("Fill start cords X and Y > ")
    val startPosX = scanner.nextInt()
    val startPosY = scanner.nextInt()
    val field: Field = Field(9, 9, countOfMines, startPosX, startPosY)

    field.buildField()
    field.printField()
    checkCell(field, countOfMines, 0)
}

fun checkCell(field: Field, COM: Int, CONM: Int) {
    var countOfMines = COM
    var countOfNonMines = CONM
    if (countOfMines == 0 && countOfNonMines == 0) {
        println("You won!")
    } else {
        val scanner = Scanner(System.`in`)
        print("Fill cords X and Y > ")
        val posX = scanner.nextInt()
        val posY = scanner.nextInt()
        val cell = field.getCell(posX, posY)
        if (cell is NonMineCell || cell is EmptyCell) {
            if (cell.value == '.' || cell.value == '*') {
                cell.check()
                countOfNonMines += 1
                field.printField()
                checkCell(field, countOfMines, countOfNonMines)
            } else if (cell is NonMineCell){
                println("Error, you checked cell with number")
                checkCell(field, countOfMines, countOfNonMines)
            } else {
                println("Error, you check empty cell")
                checkCell(field, countOfMines, countOfNonMines)
            }
        } else if (cell is MineCell) {
            if (cell.value == '.') {
                countOfMines -= 1
            } else {
                countOfMines += 1
            }
            cell.check()
            field.printField()
            checkCell(field, countOfMines, countOfNonMines)
        }
    }
}