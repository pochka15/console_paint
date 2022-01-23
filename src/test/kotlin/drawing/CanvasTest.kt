package drawing

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CanvasTest {
    @Test
    fun `create 0 by 0 canvas EXPECT correct image`() {
        val expected = ""
        assertEquals(expected, Canvas(0, 0).toString())
    }

    @Test
    fun `create 1 by 1 canvas EXPECT correct image`() {
        val expected = """---
                       #| |
                       #---""".trimMargin("#")
        assertEquals(expected, Canvas(1, 1).toString())
    }

    @Test
    fun `create 4 by 4 canvas EXPECT correct image`() {
        val expected = """------
                       #|    |
                       #|    |
                       #|    |
                       #|    |
                       #------""".trimMargin("#")
        assertEquals(expected, Canvas(4, 4).toString())
    }

    @Test
    fun `draw zero length line EXPECT correct image`() {
        val expected = """------
                       #|    |
                       #| x  |
                       #|    |
                       #|    |
                       #------""".trimMargin("#")
        val actual = Canvas(4, 4)
            .run {
                drawHorizontalLine(2, 2, 2)
                toString()
            }

        assertEquals(expected, actual)
    }

    @Test
    fun `draw vertical line EXPECT correct image`() {
        val expected = """------
                       #|    |
                       #| x  |
                       #| x  |
                       #| x  |
                       #------""".trimMargin("#")
        val actual = Canvas(4, 4)
            .run {
                drawVerticalLine(2, 2, 4)
                toString()
            }

        assertEquals(expected, actual)
    }

    @Test
    fun `draw horizontal line EXPECT correct image`() {
        val expected = """------
                       #|    |
                       #| xxx|
                       #|    |
                       #|    |
                       #------""".trimMargin("#")
        val actual = Canvas(4, 4)
            .run {
                drawHorizontalLine(2, 4, 2)
                toString()
            }

        assertEquals(expected, actual)
    }

    @Test
    fun `draw zero area rectangle EXPECT correct image`() {
        val expected = """------
                       #|x   |
                       #|    |
                       #|    |
                       #|    |
                       #------""".trimMargin("#")
        val actual = Canvas(4, 4)
            .run {
                drawRectangle(1, 1, 1, 1)
                toString()
            }

        assertEquals(expected, actual)
    }

    @Test
    fun `draw upper left rectangle EXPECT correct image`() {
        val expected = """------
                       #|xx  |
                       #|xx  |
                       #|    |
                       #|    |
                       #------""".trimMargin("#")
        val actual = Canvas(4, 4)
            .run {
                drawRectangle(1, 1, 2, 2)
                toString()
            }

        assertEquals(expected, actual)
    }

    @Test
    fun `draw rectangle with incorrect coords sequence EXPECT correct image`() {
        val expected = """------
                       #|    |
                       #| xxx|
                       #| xxx|
                       #|    |
                       #------""".trimMargin("#")
        val actual = Canvas(4, 4)
            .run {
                drawRectangle(4, 3, 2, 2)
                toString()
            }

        assertEquals(expected, actual)
    }

    @Test
    fun `draw rectangle with middle spacing EXPECT correct image`() {
        val expected = """------
                       #|xxxx|
                       #|x  x|
                       #|x  x|
                       #|xxxx|
                       #------""".trimMargin("#")
        val actual = Canvas(4, 4)
            .run {
                drawRectangle(1, 1, 4, 4)
                toString()
            }

        assertEquals(expected, actual)
    }

    @Test
    fun `fill empty canvas EXPECT canvas is filled`() {
        val expected = """------
                       #|oooo|
                       #|oooo|
                       #|oooo|
                       #|oooo|
                       #------""".trimMargin("#")
        val actual = Canvas(4, 4)
            .run {
                fillArea(1, 1, 'o')
                toString()
            }

        assertEquals(expected, actual)
    }
}