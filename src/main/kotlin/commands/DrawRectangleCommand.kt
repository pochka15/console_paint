package commands

import drawing.State


/**
 * Command that draws rectangle on canvas
 */
class DrawRectangleCommand(override val meta: CommandMeta, private val state: State) : CommandWithArg {
    override fun execute(argument: String) {
        if (state.canvas == null)
            return println("Please, create canvas before drawing")

        val arguments = argument.split("\\s+".toRegex(), limit = 4)
        if (arguments.size < 4) return println("Expected to have 4 arguments: x1, y1, x2, y2")
        val (arg1, arg2, arg3, arg4) = arguments
        val x1 = arg1.toIntOrNull() ?: return println("Couldn't parse x1: $arg1")
        val y1 = arg2.toIntOrNull() ?: return println("Couldn't parse y1: $arg2")
        val x2 = arg3.toIntOrNull() ?: return println("Couldn't parse x2: $arg3")
        val y2 = arg4.toIntOrNull() ?: return println("Couldn't parse y2: $arg4")

        state.canvas!!.run {
            drawRectangle(x1, y1, x2, y2)
            println(toString())
        }
    }
}