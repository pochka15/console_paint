package commands

/**
 * Class to create custom commands easily
 */
class CustomCommand(
    private val commandMeta: CommandMeta,
    private val executable: () -> Unit
) : CommandWithoutArg {

    override fun execute() = executable()

    override val meta: CommandMeta
        get() = commandMeta
}