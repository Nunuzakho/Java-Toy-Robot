package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.*;
import za.co.wethinkcode.toyrobot.world.AbstractWorld;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;
import za.co.wethinkcode.toyrobot.world.TurtleWorld;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Play {
    static Scanner scanner;

    /**
     * The main function is the entry point of the program. It creates a robot, a maze, and a world. It then prompts the
     * user for input and executes the commands
     */
    public static void main(String[] args) {

        //allows you to see the command line args


        scanner = new Scanner(System.in);
        Robot robot;
        Maze shirleymaze = null;
        AbstractWorld shirleyworld = null;

        String name = getInput("What do you want to name your robot?"); // if statement goes in here...
        robot = new Robot(name);
        System.out.println("Hello Kiddo!");
        if(args.length >= 1){
            if(args[0].equalsIgnoreCase("Turtle") || args[0].equalsIgnoreCase("Text")){
                if(args.length == 1){
                    shirleymaze = new RandomMaze();
                }else{
                    switch(args[1].toUpperCase()){
                        case "DESIGNEDMAZE":
                            shirleymaze = new DesignedMaze();
                            System.out.println("Loaded Designed Maze");
                            break;
                        case "EMPTYMAZE":
                            shirleymaze = new EmptyMaze();
                            System.out.println("Loaded Empty Maze");
                            break;
                        case "SIMPLEMAZE":
                            shirleymaze = new SimpleMaze();
                            System.out.println("Loaded Simple Maze");
                            break;
                        default:
                            shirleymaze = new EmptyMaze();
                            System.out.println("Loaded Random Maze");
                            break;
                    }
                }
            }
            shirleyworld = ((args[0].toLowerCase( ).equals("turtle"))) ? new TurtleWorld(shirleymaze) : new TextWorld(shirleymaze);
        }else{
            shirleyworld = new TextWorld(new RandomMaze());
        }

        robot.setWorld(shirleyworld);
        robot.getWorld().showObstacles();

        System.out.println(robot.toString());

        Command command;
        boolean shouldContinue = true;
        do {
            String instruction = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();
            try {
                command = Command.create(instruction);
                shouldContinue = robot.handleCommand(command);
            } catch (IllegalArgumentException e) {
                robot.setStatus("Sorry, I did not understand '" + instruction + "'." );
//                robot.setStatus("Sorry, I did not understand'"+ " " + "'.");
            }
            System.out.println(robot);
        } while (shouldContinue);
        if (shirleyworld.getClass().getSimpleName().equalsIgnoreCase("turtleworld")){
            System.exit(0);
        }
    }

    /**
     * It takes a string as an argument, prints it to the console, then waits for the user to enter a string. If the user
     * enters a blank string, it prints the prompt again and waits for the user to enter a string
     *
     * @param prompt The message that will be displayed to the user.
     * @return The input from the user.
     */
    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }
}
