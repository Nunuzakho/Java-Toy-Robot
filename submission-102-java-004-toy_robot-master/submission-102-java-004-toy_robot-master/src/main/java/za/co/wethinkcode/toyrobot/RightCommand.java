package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class RightCommand extends Command {
    /**
     * Turns the robot right.
     *
     * @param target The robot that is executing the command.
     * @return A boolean value.
     */
    @Override
    public boolean execute(Robot target) {
        target.getWorld().updateDirection(true);
        target.setStatus("Turned right.");
        return true;
    }
    // This is the constructor for the RightCommand class. It is calling the constructor of the super class (Command) and
    // passing in the string "right".
    public RightCommand(){
        super("right");
    }
}
