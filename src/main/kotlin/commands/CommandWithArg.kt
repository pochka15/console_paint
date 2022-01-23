package commands

/**
 * Interface representing commands that take argument for the execution
 */
interface CommandWithArg : Command {
    fun execute(argument: String)
}