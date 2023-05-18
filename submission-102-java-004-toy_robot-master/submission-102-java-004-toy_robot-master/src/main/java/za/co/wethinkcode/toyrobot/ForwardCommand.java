package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class ForwardCommand extends Command {
    /**
     * The function takes a robot as an argument and moves it forward by the number of steps specified in the argument
     *
     * @param target The robot that is executing the command.
     * @return The boolean value true is being returned.
     */
    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());
        if (target.getWorld().updatePosition(nrSteps).equals(IWorld.UpdateResponse.SUCCESS)){
            target.setStatus("Moved forward by "+nrSteps+" steps.");
        }else if(target.getWorld().updatePosition(nrSteps).equals(IWorld.UpdateResponse.FAILED_OBSTRUCTED)){
            target.setStatus("Sorry, there is an obstacle in the way.");
        }else {
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        }
        return true;
    }

    // This is the constructor for the ForwardCommand class. It is calling the constructor of the super class (Command) and
    // passing the name of the command and the argument.
    public ForwardCommand(String argument) {
        super("forward", argument);
    }
}

