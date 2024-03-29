package commands

import drawing.State


/**
 * Command that draws line on canvas
 */
class DrawLineCommand(override val meta: CommandMeta, private val state: State) : CommandWithArg {
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

        if (x1 == x2 || y1 == y2) {
            state.canvas!!.run {
                if (x1 == x2) drawVerticalLine(x1, y1, y2)
                else drawHorizontalLine(x1, x2, y1)
                println(toString())
            }
        } else return println(
            "Cannot draw line. " +
                    "Currently only horizontal and vertical lines are supported"
        )
    }
}