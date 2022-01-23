package drawing

import java.lang.Integer.max
import java.lang.Integer.min

private const val spacingForBoundaries = 2

/**
 * Class representing canvas for drawing ascii letters
 */
class Canvas(width: Int, height: Int) {
    private val columnsAmount: Int
    private val rowsAmount: Int
    private var rows: Array<Array<Char>>

    val size: Pair<Int, Int>

    init {
        val spacing = if (width > 0) spacingForBoundaries else 0
        columnsAmount = width + spacing
        rowsAmount = height + spacing
        size = columnsAmount to rowsAmount
        rows = Array(rowsAmount) { row ->
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
        return rows
            .joinToString(System.lineSeparator()) {
                // Join columns
                it.joinToString(separator = "")
            }
    }

    fun drawHorizontalLine(x1: Int, x2: Int, y: Int) {
        assert(x1 > 0 && x2 > 0 && y > 0) { "Line coordinates must be > 0" }
        for (i in min(x1, x2)..max(x1, x2)) rows[y][i] = 'x'
    }


    fun drawVerticalLine(x: Int, y1: Int, y2: Int) {
        assert(x > 0 && y1 > 0 && y2 > 0) { "Line coordinates must be > 0" }
        for (i in min(y1, y2)..max(y1, y2)) rows[i][x] = 'x'
    }


    fun drawRectangle(x1: Int, y1: Int, x2: Int, y2: Int) {
        val fromRow = min(y1, y2)
        val toRow = max(y1, y2)
        val fromColumn = min(x1, x2)
        val toColumn = max(x1, x2)

        for (i in fromRow..toRow) {
            for (j in fromColumn..toColumn) {
                rows[i][j] = 'x'
            }
        }

    }
}