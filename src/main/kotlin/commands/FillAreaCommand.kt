package commands

import drawing.State


/**
 * Command that draws a rectangle on canvas
 */
class FillAreaCommand(override val meta: CommandMeta, private val state: State) : CommandWithArg {
    override fun execute(argument: String) {
        if (state.canvas == null)
            return println("Please, create canvas before drawing")

        val arguments = argument.split("\\s+".toRegex(), limit = 3)
        if (arguments.size < 3) return println("Expected to have 3 arguments: x, y, c")
        val (arg1, arg2, arg3) = arguments
        val x = arg1.toIntOrNull() ?: return println("Couldn't parse x: $arg1")
        val y = arg2.toIntOrNull() ?: return println("Couldn't parse y: $arg2")
        val c = arg3[0]

        state.canvas!!.run {
            fillArea(x, y, c)
            println(toString())
        }
    }
}