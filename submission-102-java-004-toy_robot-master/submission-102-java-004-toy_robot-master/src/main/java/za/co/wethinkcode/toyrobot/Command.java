package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.SimpleMazeRunner;
import za.co.wethinkcode.toyrobot.world.IWorld;

public abstract class Command {
    private final String name;
    private String argument;

    public abstract boolean execute(Robot target);

    // This is a constructor for the Command class. It takes a string as an argument and sets the name of the command to
    // the string. It also sets the argument to an empty string.
    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";
    }

    // This is a constructor for the Command class. It takes a string as an argument and sets the name of the command to
    // the string. It also sets the argument to an empty string.
    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    /**
     * //<3>
     *
     * // Java
     * public String getName() {                                                                           //<4>
     *         return name;
     *     }
     *
     * @return The name of the person.
     */
    public String getName() {                                                                           //<2>
        return name;
    }

    /**
     * This function returns the argument of the current object.
     *
     * @return The argument of the object.
     */
    public String getArgument()
    {
        return this.argument;
    }

    /**
     * It takes a string, splits it into an array of strings, and then uses a switch statement to create a new command
     * object based on the first element of the array
     *
     * @param instruction The instruction that the user has entered.
     * @return A Command object.
     */
    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]){
            case "shutdown":
            case "off":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
                if(args.length == 2){
                    return new ForwardCommand(args[1]);
                }
            case "back":
                if(args.length == 2){
                    return new BackCommand(args[1]);
                }
            case "left":
                return new LeftCommand();
            case "right":
                return new RightCommand();
            case "sprint":
                return new SprintCommand(args[1]);
            case "replay":
                if (args.length == 1){
                    return new ReplayCommand();
                }else if(args.length == 2){
                    return new ReplayCommand(args[1]);
                }else if(args.length == 3){
                    return new ReplayCommand(args[1]+" "+args[2]);
                }
            case "mazerun":
                if(args.length == 1){
                    return new SimpleMazeRunner();
                }
                return new SimpleMazeRunner(args[1]);

            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }
}

