package drawing

// Spacing for 2 characters
private const val additionalSpacing = 2

/**
 * Class representing canvas for drawing ascii letters
 */
class Canvas(width: Int, height: Int) {
    private var row: Array<Array<Char>>

    init {
        val spacing = if (width > 0) additionalSpacing else 0
        val columnsAmount = width + spacing
        val rowsAmount = height + spacing
        row = Array(rowsAmount) { row ->
            Array(columnsAmount) { column ->
                getInitialChar(row, column, columnsAmount, rowsAmount)
            }
        }
    }

    private fun getInitialChar(
        row: Int,
        column: Int,
        width: Int,
        height: Int
    ): Char {

        if (row == 0 || row == height - 1) return '-'
        if (column == 0 || column == width - 1) return '|'
        return ' '
    }

    override fun toString(): String {
        return row
            .joinToString(System.lineSeparator()) {
                // Join columns
                it.joinToString(separator = "")
            }
    }
}