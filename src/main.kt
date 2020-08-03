import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    print("Fill number of mines > ")
    val countOfMines = scanner.nextInt()
    val field: Field = Field(9, 9, countOfMines)

    field.buildField()
    field.printField()
    checkCell(field, countOfMines, 0)
}

fun openCell(field: Field) {

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
        if (cell is NonMineCell) {
            if (cell.value == '.' || cell.value == '*') {
                cell.check()
                countOfNonMines += 1
                field.printField()
                checkCell(field, countOfMines, countOfNonMines)
            } else {
                println("Error, you checked cell with number")
                checkCell(field, countOfMines, countOfNonMines)
            }
        } else if (cell is MineCell) {
            cell.check()
            countOfMines -= 1
            field.printField()
            checkCell(field, countOfMines, countOfNonMines)
        }
    }
}