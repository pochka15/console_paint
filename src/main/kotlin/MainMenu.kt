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
            CommandMeta("C", "Args: w, h. Create a new canvas of width w and height h."),
            state
        ),

        DrawLineCommand(
            CommandMeta(
                "L", """Args: x1, y1, x2, y2. Draw a new line from (x1,y1) to (x2,y2).
    Currently only horizontal or vertical lines are
    supported. Horizontal and vertical lines will be drawn
    using the 'x' character"""
            ),
            state
        ),

        DrawRectangleCommand(
            CommandMeta(
                "R", """Args: x1, y1, x2, y2. Draw a new rectangle, whose upper left corner
    is (x1,y1) and lower right corner is (x2,y2).
    Horizontal and vertical lines will be drawn using the
    'x' character."""
            ),
            state
        ),

        FillAreaCommand(
            CommandMeta(
                "B", """Args: x, y, c. Should fill the entire area connected to (x,y) with
    "colour" c. The behaviour of this is the same as that
    of the "bucket fill" tool in paint programs."""
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