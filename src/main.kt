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

fun checkCell(field: Field, COM: Int, CONM: Int) {
    val scanner = Scanner(System.`in`)
    var countOfMines = COM
    var countOfNonMines = CONM
    print("Fill cords X and Y > ")
    val posX = scanner.nextInt()
    val posY = scanner.nextInt()
    val cell = field.getCell(posX, posY)

    if (countOfMines == 0 && countOfNonMines == 0) {
        println("You won!")
    } else {
        if (cell is NumberCell) {
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