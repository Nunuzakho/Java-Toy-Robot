package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class HelpCommand extends Command {

    // This is the constructor for the HelpCommand class. It is calling the constructor of the super class (Command) and
    // passing in the string "help".
    public HelpCommand() {
        super("help");
    }

    /**
     * The function takes in a Robot object and returns a boolean value
     *
     * @param target The robot that the command is being executed on.
     * @return The method is returning a boolean value.
     */
    @Override
    public boolean execute(Robot target) {
        target.setStatus("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n" +
                "BACK - move back by specified number of steps, e.g. 'BACK 10'\n" +
                "LEFT - robot turns left, e.g 'left'\n" +
                "RIGHT - robot turns right, e.g 'right'\n" +
                "SPRINT - robot sprints forward by a specified number of steps, e.g 'SPRINT 10'\n" +
                "MAZERUN - robot mazeruns through a maze by finding the nearest pathway defaulting to the top, e.g 'mazerun'\n" +
                "MAZERUN TOP - robot finds its own pathway to the top, e.g 'mazerun top'\n" +
                "MAZERUN  BOTTOM - robot finds its own pathway to the bottom, e.g 'mazerun bottom'\n" +
                "MAZERUN LEFT - robot finds its own pathway to the left, e.g 'mazerun left'\n" +
                "MAZERUN RIGHT - robot finds its own pathway to the right, e.g 'mazerun right'");
        return true;
    }
}
