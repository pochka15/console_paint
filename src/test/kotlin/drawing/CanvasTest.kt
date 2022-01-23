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
    fun `create 20 by 4 canvas EXPECT correct image`() {
        val expected = """
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------""".trimMargin("#")
        assertEquals(expected, Canvas(20, 4).toString())
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

    @Test
    fun `fill canvas that contains line EXPECT canvas is filled correctly`() {
        val expected = """------
                       #|oooo|
                       #|oxxo|
                       #|oooo|
                       #|oooo|
                       #------""".trimMargin("#")
        val actual = Canvas(4, 4)
            .run {
                drawHorizontalLine(2, 3, 2)
                fillArea(3, 4, 'o')
                toString()
            }

        assertEquals(expected, actual)
    }

    @Test
    fun `fill inside rectangle EXPECT canvas is filled correctly`() {
        val expected = """
---------
#|       |
#| xxxxx |
#| xooox |
#| xooox |
#| xxxxx |
#|       |
#|       |
---------""".trimMargin("#")
        val actual = Canvas(7, 7)
            .run {
                drawRectangle(2, 2, 6, 5)
                fillArea(4, 3, 'o')
                toString()
            }

        assertEquals(expected, actual)
    }

    @Test
    fun `fill outside rectangle EXPECT canvas is filled correctly`() {
        val expected = """
---------
#|ooooooo|
#|oxxxxxo|
#|ox   xo|
#|ox   xo|
#|oxxxxxo|
#|ooooooo|
#|ooooooo|
---------""".trimMargin("#")
        val actual = Canvas(7, 7)
            .run {
                drawRectangle(2, 2, 6, 5)
                fillArea(2, 6, 'o')
                toString()
            }

        assertEquals(expected, actual)
    }

    @Test
    fun `make random fill 1 EXPECT canvas is filled correctly`() {
        val expected = """
---------
#|ooooooo|
#|oxxxxxo|
#|xx   xo|
#| x   xo|
#|xxxxxxo|
#|    xoo|
#|    xoo|
---------""".trimMargin("#")
        val actual = Canvas(7, 7)
            .run {
                drawRectangle(2, 2, 6, 5)
                drawVerticalLine(1, 3, 3)
                drawHorizontalLine(1, 5, 5)
                drawVerticalLine(5, 6, 7)
                fillArea(1, 1, 'o')
                toString()
            }

        assertEquals(expected, actual)
    }


    @Test
    fun `make random fill 2 EXPECT canvas is filled correctly`() {
        val expected = """
---------
#|       |
#| xxxxx |
#|xx   x |
#|ox   x |
#|xxxxxx |
#|    x  |
#|    x  |
---------""".trimMargin("#")
        val actual = Canvas(7, 7)
            .run {
                drawRectangle(2, 2, 6, 5)
                drawVerticalLine(1, 3, 3)
                drawHorizontalLine(1, 5, 5)
                drawVerticalLine(5, 6, 7)
                fillArea(1, 4, 'o')
                toString()
            }

        assertEquals(expected, actual)
    }

    @Test
    fun `test random canvas 1 EXPECT correct image`() {
        val expected = """
----------------------
|                    |
|xxxxxx              |
|                    |
|                    |
----------------------""".trimMargin("#")
        val actual = Canvas(20, 4)
            .run {
                drawHorizontalLine(1, 6, 2)
                toString()
            }

        assertEquals(expected, actual)
    }

    @Test
    fun `test random canvas 2 EXPECT correct image`() {
        val expected = """
----------------------
|                    |
|xxxxxx              |
|     x              |
|     x              |
----------------------""".trimMargin("#")
        val actual = Canvas(20, 4)
            .run {
                drawHorizontalLine(1, 6, 2)
                drawVerticalLine(6, 3, 4)
                toString()
            }

        assertEquals(expected, actual)
    }

    @Test
    fun `test random canvas 3 EXPECT correct image`() {
        val expected = """
----------------------
#|               xxxxx|
#|xxxxxx         x   x|
#|     x         xxxxx|
#|     x              |
----------------------""".trimMargin("#")
        val actual = Canvas(20, 4)
            .run {
                drawHorizontalLine(1, 6, 2)
                drawVerticalLine(6, 3, 4)
                drawRectangle(16, 1, 20, 3)
                toString()
            }

        assertEquals(expected, actual)
    }

    @Test
    fun `test random canvas 4 EXPECT correct image`() {
        val expected = """
----------------------
#|oooooooooooooooxxxxx|
#|xxxxxxooooooooox   x|
#|     xoooooooooxxxxx|
#|     xoooooooooooooo|
----------------------""".trimMargin("#")
        val actual = Canvas(20, 4)
            .run {
                drawHorizontalLine(1, 6, 2)
                drawVerticalLine(6, 3, 4)
                drawRectangle(16, 1, 20, 3)
                fillArea(10, 3, 'o')
                toString()
            }

        assertEquals(expected, actual)
    }


}