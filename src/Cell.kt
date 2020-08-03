open class Cell(val posX: Int, val posY: Int) {

    val defaultChar = '.'
    open var isChecked = false
    open var value = defaultChar

    open fun check() {
        isChecked = !isChecked
        value = if (isChecked) defaultChar else defaultChar
    }
}

class MineCell(posX: Int, posY: Int): Cell(posX, posY) {

    override fun check() {
        isChecked = !isChecked
        value = if (isChecked) '*' else defaultChar
    }
}

class NonMineCell(posX: Int, posY: Int): Cell(posX, posY) {
    override fun check() {
        isChecked = !isChecked
        value = if (value == '.') {
            if (isChecked) '*' else defaultChar
        } else {
            if (isChecked) value else defaultChar
        }
    }
}

class EmptyCell(posX: Int, posY: Int): Cell(posX, posY) {
    override var value = '-'
}