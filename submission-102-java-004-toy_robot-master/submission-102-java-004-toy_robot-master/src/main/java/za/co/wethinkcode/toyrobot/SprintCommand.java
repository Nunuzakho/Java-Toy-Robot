package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class SprintCommand extends Command{
    /**
     * The function takes a robot as an argument and moves it forward the number of steps specified in the argument
     *
     * @param target The robot that will execute the command.
     * @return A boolean value.
     */
    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());
        for (int i = nrSteps; i > 0; i--){
            Command command = Command.create("forward " + String.valueOf(i));
            command.execute(target);
            if(i>1){
                System.out.println(target.toString()); //dont print... get status
            }
        }
        return true;
    }

    // A constructor for the SprintCommand class. It is calling the constructor of the super class (Command) and passing
    // the name of the command and the argument.
    public SprintCommand(String argument) {
        super("sprint", argument);
    }
}
