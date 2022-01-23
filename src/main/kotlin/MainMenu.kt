import commands.*
import drawing.State


private const val QUIT_COMMAND_NAME = "Q"

class MainMenu(state: State) {
    private val nameToCommand: Map<String, Command>

    private val commandsDescription: String by lazy {
        commands.joinToString(separator = System.lineSeparator() + System.lineSeparator()) {
            it.meta.toString()
        }
    }

    private val commands = listOf(
        CreateCanvasCommand(
            CommandMeta("C", "Create a new canvas of width w and height h."),
            state
        ),

        DrawLineCommand(
            CommandMeta(
                "L", """Draw a new line from (x1,y1) to (x2,y2).
    Currently only horizontal or vertical lines are
    supported. Horizontal and vertical lines will be drawn
    using the 'x' character"""
            ),
            state
        ),

        CustomCommand(CommandMeta(QUIT_COMMAND_NAME, "Quit the program")) {
            println("Good bye!")
        },

        CustomCommand(CommandMeta("Help", "Get help on commands")) {
            println(commandsDescription)
        },
    )


    init {
        this.nameToCommand = commands.associateBy { it.meta.name }
    }


    fun enter() {
        println("Enter \"Help\" to see all the available commands")
        var shouldFinish = false
        while (!shouldFinish) {
            print("${System.lineSeparator()}Enter command: ")

//            Read command name and arg
            val nameAndArg = readLine()!!.split(" ", limit = 2)
            val name = nameAndArg[0]
            val arg = nameAndArg.getOrNull(1)

//            Execute command
            val command = this.nameToCommand[name]
            if (command != null) {
                if (command.meta.name == QUIT_COMMAND_NAME) shouldFinish = true

                when (command) {
                    is CommandWithArg -> command.execute(arg!!)
                    is CommandWithoutArg -> command.execute()
                }

            } else println("Unknown command entered")
        }
    }
}