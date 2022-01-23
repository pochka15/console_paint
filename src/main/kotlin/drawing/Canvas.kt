package drawing

import algorithms.floodFill
import java.lang.Integer.max
import java.lang.Integer.min

private const val spacingForBoundaries = 2

typealias Point = Pair<Int, Int>

data class Size(val columnsAmount: Int, val rowsAmount: Int)

/**
 * Class representing canvas for drawing ascii letters
 */
class Canvas(width: Int, height: Int) {
    private var columns: Array<CharArray>

    @Suppress("MemberVisibilityCanBePrivate")
    val size: Size

    init {
        val spacing = if (width > 0) spacingForBoundaries else 0
        size = Size(width + spacing, height + spacing)
        columns = Array(size.columnsAmount) { column ->
            CharArray(size.rowsAmount) { row -> getInitialChar(column, row) }
        }
    }

    /**
     * Get character which is initially drawn on canvas at position (column, row)
     */
    private fun getInitialChar(
        column: Int,
        row: Int,
    ): Char {

        if (row == 0 || row == size.rowsAmount - 1) return '-'
        if (column == 0 || column == size.columnsAmount - 1) return '|'
        return ' '
    }

    override fun toString(): String {
        if (size.rowsAmount == 0) return ""

        val newLineCharsAmount = size.rowsAmount - 1
        val builder = StringBuilder(size.rowsAmount * size.columnsAmount + newLineCharsAmount)
        for (row in 0 until size.rowsAmount) {
            for (column in 0 until size.columnsAmount) {
                builder.append(columns[column][row])
            }
            if (row != size.rowsAmount - 1) builder.appendLine()
        }
        return builder.toString()
    }

    fun drawHorizontalLine(x1: Int, x2: Int, y: Int) {
        assert(x1 > 0 && x2 > 0 && y > 0) { "Line coordinates must be > 0" }
        for (i in min(x1, x2)..max(x1, x2)) columns[i][y] = 'x'
    }


    fun drawVerticalLine(x: Int, y1: Int, y2: Int) {
        assert(x > 0 && y1 > 0 && y2 > 0) { "Line coordinates must be > 0" }
        for (i in min(y1, y2)..max(y1, y2)) columns[x][i] = 'x'
    }


    fun drawRectangle(x1: Int, y1: Int, x2: Int, y2: Int) {
        val minY = min(y1, y2)
        val maxY = max(y1, y2)
        val minX = min(x1, x2)
        val maxX = max(x1, x2)

        drawHorizontalLine(minX, maxX, minY)
        drawHorizontalLine(minX, maxX, maxY)
        drawVerticalLine(minX, minY, maxY)
        drawVerticalLine(maxX, minY, maxY)
    }

    fun fillArea(x: Int, y: Int, color: Char) {
        assert(size.rowsAmount > 2 && size.columnsAmount > 2) { "Cannot fill area, current size: ${size.columnsAmount}:${size.rowsAmount}" }
        floodFill(
            columns,
            x,
            y,
            1 to 1,
            size.columnsAmount - 1 to size.rowsAmount - 1,
            columns[x][y],
            color
        )
    }
}