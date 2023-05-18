package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class BackCommand extends Command {
    /**
     * If the robot can move back by the number of steps specified in the argument, then it will move back and set its
     * status to a message that says it moved back by that number of steps. Otherwise, it will set its status to a message
     * that says it cannot go outside its safe zone.
     *
     * @param target The robot that is executing the command.
     * @return The boolean value true.
     */
    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());
        if (target.getWorld().updatePosition(nrSteps*(-1)).equals(IWorld.UpdateResponse.SUCCESS)){
            target.setStatus("Moved back by "+nrSteps+" steps.");
        } else {
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        }
        return true;
    }

    // This is the constructor for the BackCommand class. It is calling the constructor of the Command class, which is its
    // parent class.
    public BackCommand(String argument){
        super("back", argument);
    }
}
