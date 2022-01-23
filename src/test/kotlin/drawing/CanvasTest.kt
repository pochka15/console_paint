package drawing

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CanvasTest {
    @Test
    fun `create 0 by 0 canvas EXPECT correct rendering`() {
        val expected = ""
        assertEquals(expected, Canvas(0, 0).toString())
    }

    @Test
    fun `create 1 by 1 canvas EXPECT correct rendering`() {
        val expected = """---
                       #| |
                       #---""".trimMargin("#")
        assertEquals(expected, Canvas(1, 1).toString())
    }

    @Test
    fun `create 4 by 4 canvas EXPECT correct rendering`() {
        val expected = """------
                       #|    |
                       #|    |
                       #|    |
                       #|    |
                       #------""".trimMargin("#")
        assertEquals(expected, Canvas(4, 4).toString())
    }
}