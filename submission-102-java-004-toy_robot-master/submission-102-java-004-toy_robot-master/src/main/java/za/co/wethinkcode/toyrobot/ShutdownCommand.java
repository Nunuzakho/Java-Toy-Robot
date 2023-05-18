package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

/**
 * `ShutdownCommand` is a subclass of `Command` that has a constructor that calls the `Command` constructor with the string
 * `"off"`
 */
public class ShutdownCommand extends Command {
    public ShutdownCommand() {
        super("off");
    }

    /**
     * If the robot is told to shut down, it will set its status to 'Shutting down...' and return false.
     *
     * @param target The robot that the command is being executed on.
     * @return A boolean value.
     */
    @Override
    public boolean execute(Robot target) {
        target.setStatus("Shutting down...");
        return false;
    }
}
