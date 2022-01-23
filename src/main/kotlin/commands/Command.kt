package commands

/**
 * Interface for the menu commands
 */
sealed interface Command {
    val meta: CommandMeta
}