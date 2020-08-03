open class Cell(val posX: Int, val posY: Int) {

    open var isChecked = false
    open var value = if (isChecked) '*' else '.'

    fun check() {
        isChecked = !isChecked
    }
}

class MineCell(posX: Int, posY: Int): Cell(posX, posY) {
    override var isChecked = false
}

class EmptyCell(posX: Int, posY: Int): Cell(posX, posY) {
    override var isChecked = false
}