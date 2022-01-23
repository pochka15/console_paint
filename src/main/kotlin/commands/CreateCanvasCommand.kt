package commands

import drawing.Canvas
import drawing.State


/**
 * Command that creates canvas
 */
class CreateCanvasCommand(
    override val meta: CommandMeta,
    private val state: State
) : CommandWithArg {
    override fun execute(argument: String) {
        val (arg1, arg2) = argument.split("\\s+".toRegex(), limit = 2)
        val width = arg1.toIntOrNull() ?: return println("Couldn't parse width: $arg1")
        val height = arg2.toIntOrNull() ?: return println("Couldn't parse height: $arg2")
        state.canvas = Canvas(width, height).also { println(it) }
    }
}