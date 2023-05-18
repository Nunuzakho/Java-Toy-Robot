package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ReplayCommand extends Command {
    /**
     * It takes a list of commands, reverses them, and then executes them
     *
     * @param target The robot that will be executing the command.
     * @return A boolean value.
     */
    @Override
    public boolean execute(Robot target){
        String[] arguments = getArgument().split(" ");
        boolean reversed = hasReversed(arguments);
        int start = 0;
        int end = 0;

        if(arguments.length > 0){
            String number = getNumberFromArguments(arguments);
            if(isNumeric(number)){
                end=Integer.parseInt(number);
            }
            else if(number.contains("-")){
                String[] startAndEnd = number.split("-");
                start = Integer.parseInt(startAndEnd[0]);
                end = Integer.parseInt(startAndEnd[1]);
            }
        }

        List<Command> commands = target.getCommands(start,end);
        Collections.reverse(commands);

        if(reversed){
            Collections.reverse(commands);
        }
        for(Command move:commands){
            move.execute(target);
            System.out.println(target.toString());
        }
        target.setStatus("replayed "+commands.size()+" commands.");
        return true;
    }
    /**
     * If the string is empty, return false. Otherwise, loop through the string and check if each character is a digit. If
     * it is, return true. If it isn't, return false
     *
     * @param argument The string that is being checked to see if it is a number.
     * @return The method is returning a boolean value.
     */
    public boolean isNumeric(String argument){
        if(argument.length() == 0){
            return false;
        }
        for (int i = 0; i < argument.length(); i ++){
            char digit = argument.charAt(i);
            if (!Character.isDigit(digit)){
                return false;
            }
        }
        return true;
    }

    /**
     * If the first argument is "reversed", return the second argument, otherwise return the first argument
     *
     * @param arguments The arguments passed to the command.
     * @return The number that is being searched for.
     */
    public String getNumberFromArguments(String[] arguments){
            if(arguments.length == 1 && arguments[0].equalsIgnoreCase("reversed")){
                return arguments[0];
            }else if(arguments[0].equalsIgnoreCase("reversed")){
                return arguments[1];
            }else{
                return arguments[0];
            }
    }

    // Calling the constructor of the super class, Command, and passing it the arguments "replay" and "argument".
    public ReplayCommand(){
        super("replay");
    }
    // Calling the constructor of the super class, Command, and passing it the arguments "replay" and "argument".
    public ReplayCommand(String argument){
        super("replay", argument);
    }
    /**
     * If the arguments array contains the string 'reversed', return true, otherwise return false.
     *
     * @param arguments The arguments passed to the command.
     * @return The method is returning a boolean value.
     */
    public boolean hasReversed(String[] arguments){
        for(String argument:arguments){
            if(argument.equals("reversed")){
                return true;
            }
        }
        return false;

    }

}
