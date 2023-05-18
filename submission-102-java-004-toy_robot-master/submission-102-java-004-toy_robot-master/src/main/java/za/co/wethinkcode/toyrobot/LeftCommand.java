package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class LeftCommand extends Command{
    /**
     * Turns the robot left.
     *
     * @param target The robot that is executing the command.
     * @return A boolean value.
     */
    @Override
    public boolean execute(Robot target) {
        target.getWorld().updateDirection(false);
        target.setStatus("Turned left.");
        return true;
    }

    // This is the constructor for the LeftCommand class. It is calling the constructor of the super class (Command) and
    // passing in the string "left".
    public LeftCommand(){
        super("left");
    }
}
