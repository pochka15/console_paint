package commands

/**
 * Metadata for the commands
 */
data class CommandMeta(val name: String, val description: String) {
    override fun toString(): String {
        return "$name: $description"
    }
}
