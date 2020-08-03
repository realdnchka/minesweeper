open class Cell(val posX: Int, val posY: Int) {

    open var isChecked = false
    open var value = '.'

    open fun check() {
        isChecked = !isChecked
    }
}

class MineCell(posX: Int, posY: Int): Cell(posX, posY) {

    override fun check() {
        isChecked = !isChecked
        value = if (isChecked) '*' else '.'
    }
}

class NumberCell(posX: Int, posY: Int): Cell(posX, posY) {
    override fun check() {
        isChecked = !isChecked
        if (value == '.') {
            value = if (isChecked) '*' else '.'
        } else {
            value = if (isChecked) value else '.'
        }
    }
}