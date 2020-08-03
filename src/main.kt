import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    print("Fill number of mines > ")
    val countOfMines = scanner.nextInt()
    val field: Field = Field(9, 9, countOfMines)

    field.buildField()
    field.checkCell(1, 1)
    field.printField()
    println(field.getCell(1, 1).value)
}