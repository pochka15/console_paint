package commands


/**
 * Command that creates canvas
 */
class CreateCanvasCommand(override val meta: CommandMeta) : CommandWithArg {
    override fun execute(argument: String) {
        println("Building canvas, argument: $argument")
    }
}