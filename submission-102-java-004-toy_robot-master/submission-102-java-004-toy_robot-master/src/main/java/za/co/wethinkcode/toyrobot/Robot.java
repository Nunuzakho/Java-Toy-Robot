package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.EmptyMaze;

import za.co.wethinkcode.toyrobot.world.AbstractWorld;

import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;

import java.util.*;

public class Robot {


    private AbstractWorld world;
    private String status;
    private String name;
    private ArrayList<Command> history = new ArrayList<>();

    // Creating a new robot with a name, status and world.
    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.world = new TextWorld(new EmptyMaze());
    }

    /**
     * This function returns the world that the entity is in.
     *
     * @return The world object.
     */
    public IWorld getWorld() {
        return world;
    }

    /**
     * This function returns the history of commands.
     *
     * @return The history of commands.
     */
    public ArrayList<Command> getHistory() {
        return history;
    }

    /**
     * This function returns the status of the current object
     *
     * @return The status of the ticket.
     */
    public String getStatus() {
        return this.status;
    }


    /**
     * If the command is executed successfully, add it to the history.
     *
     * @param command The command to be executed.
     * @return A boolean value.
     */
    public boolean handleCommand(Command command) {
        addCommandToHistory(command);
        return command.execute(this);

    }
    /**
     * If the command is a valid move, add it to the history
     *
     * @param command The command to be added to the history.
     */
    public void addCommandToHistory(Command command){
        String[] moves = {"forward", "back", "left", "right", "sprint"};
        for(String move:moves){
            if(move.equalsIgnoreCase(command.getName())){
                history.add(command);
            }
        }

    }

    /**
     * If the start and end are both 0, return the entire history. If the start is 0 and the end is not, return the history
     * from the beginning to the end. If the start and end are not 0, return the history from the end to the start
     *
     * @param start the index of the first command to return
     * @param end The index of the last command to return.
     * @return A list of commands from the history list.
     */
    public List<Command> getCommands(int start, int end){
        Collections.reverse(history);
        if(start == 0 && end == 0){
            return history;
        } else if(start == 0 && end != 0){
            return history.subList(0,end);
        }else{
            return history.subList(end, start);
        }
    }
    /**
     * The toString() function is a function that is called when you try to print an object
     *
     * @return The position of the world, the name of the world, and the status of the world.
     */
    @Override
    public String toString() {
       return "[" + world.getPosition().getX() + "," + world.getPosition().getY() + "] "
               + this.name + "> " + this.status;
    }


    /**
     * This function sets the status of the current object to the status passed in as a parameter
     *
     * @param status The status of the job.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This function returns the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * This function sets the world variable to the world that is passed in.
     *
     * @param world The world that the entity is in.
     */
    public void setWorld(AbstractWorld world) {
        this.world = world;
    }
}
