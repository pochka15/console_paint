package algorithms

import drawing.Point

/**
 * Flood fill algorithm taken from https://www.educative.io/edpresso/what-is-the-flood-fill-algorithm
 */
fun floodFill(
    canvas: Array<CharArray>,
    x: Int,
    y: Int,
    upperLeftCorner: Point,
    bottomRightCorner: Point,
    initialColor: Char,
    newColor: Char
) {
    // Base cases
    if (x < upperLeftCorner.first || x > bottomRightCorner.first
        || y < upperLeftCorner.second || y > bottomRightCorner.second
    ) return
    if (canvas[x][y] != initialColor) return

    // Replace the color at cell (x, y) 
    canvas[x][y] = newColor

    // Recursively call for north, east, south and west 
    floodFill(canvas, x + 1, y, upperLeftCorner, bottomRightCorner, initialColor, newColor)
    floodFill(canvas, x - 1, y, upperLeftCorner, bottomRightCorner, initialColor, newColor)
    floodFill(canvas, x, y + 1, upperLeftCorner, bottomRightCorner, initialColor, newColor)
    floodFill(canvas, x, y - 1, upperLeftCorner, bottomRightCorner, initialColor, newColor)
}
