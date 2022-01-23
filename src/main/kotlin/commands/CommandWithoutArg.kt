package commands

/**
 * Interface representing commands that are executed without argument
 */
interface CommandWithoutArg : Command {
    fun execute()
}