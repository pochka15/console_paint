package commands


/**
 * Command that draws line on canvas
 */
class DrawLineCommand(override val meta: CommandMeta) : CommandWithArg {
    override fun execute(argument: String) {
        println("Building line, argument: $argument")
    }
}