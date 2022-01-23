import commands.*
import commands.CreateCanvasCommand
import commands.DrawLineCommand
import commands.CustomCommand
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


private const val QUIT_COMMAND_NAME = "Q"

class MainMenu {
    private val nameToCommand: Map<String, Command>

    private val commandsDescription: String by lazy {
        commands.joinToString(separator = System.lineSeparator() + System.lineSeparator()) {
            it.meta.toString()
        }
    }

    private val commands = listOf(
        CreateCanvasCommand(
            CommandMeta("C", "Create a new canvas of width w and height h.")
        ),

        DrawLineCommand(
            CommandMeta(
                "L", """Draw a new line from (x1,y1) to (x2,y2).
    Currently only horizontal or vertical lines are
    supported. Horizontal and vertical lines will be drawn
    using the 'x' character"""
            )
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

//            Read command name
            val reader = BufferedReader(InputStreamReader(System.`in`))
            val name = try {
                reader.readLine().split(" ").toTypedArray()[0]
            } catch (e: IOException) {
                e.printStackTrace()
                ""
            }

//            Execute command
            val command = this.nameToCommand[name]
            if (command != null) {
                if (command.meta.name == QUIT_COMMAND_NAME) shouldFinish = true

                when (command) {
                    is CommandWithArg -> command.execute("some argument")
                    is CommandWithoutArg -> command.execute()
                }

            } else println("Unknown command entered")
        }
    }
}