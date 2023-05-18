package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Command;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.world.IWorld;

import java.util.*;

import static za.co.wethinkcode.toyrobot.world.IWorld.BOTTOM_RIGHT;
import static za.co.wethinkcode.toyrobot.world.IWorld.TOP_LEFT;

public class SimpleMazeRunner extends Command implements MazeRunner {
int mazeRunCost = 0;


    public SimpleMazeRunner(){
        super("mazerun");
    }

    // A constructor that takes a string argument.
    public SimpleMazeRunner(String argument) {
        super("mazerun", argument);

    }



    /**
     * The function takes in a robot and a direction, and then moves the robot to the edge of the maze in the given
     * direction
     *
     * @param target The robot that is executing the command.
     * @return The method is returning a boolean value.
     */
    @Override
    public boolean execute(Robot target){
        String arg = getArgument();

        switch (arg){
            case "top":
                mazeRun(target, IWorld.Direction.UP);
                break;
            case "right":
                mazeRun(target, IWorld.Direction.RIGHT);
                break;
            case "bottom":
                mazeRun(target, IWorld.Direction.DOWN);
                break;
            case "left":
                mazeRun(target, IWorld.Direction.LEFT);
                break;
            default:
                arg = "top";
                mazeRun(target, IWorld.Direction.UP);
                break;

        }
        target.setStatus("I am at the "+ arg +" edge. (Cost: "+ mazeRunCost+" steps");
        return true;
    }

    /**
     * The function takes in a target robot and an edge direction, and then it finds the shortest path to the target robot,
     * and then it moves the robot to the target robot
     *
     * @param target The robot that will be running the maze.
     * @param edgeDirection The direction of the edge of the maze.
     * @return A boolean value.
     */
    @Override
    public boolean mazeRun(Robot target, IWorld.Direction edgeDirection) {

        List<Position> path = getPath(target.getWorld());
        int pathLength = path.size();
        for(int i = 0; i < pathLength; i ++){
            Position currentPosition = target.getWorld().getPosition();
            Position nextPosition = path.remove((path.size()-1));
            Command command;
            IWorld.Direction neededDirection;
            if(nextPosition.getX()> currentPosition.getX()){
                neededDirection = IWorld.Direction.RIGHT;
            } else if (nextPosition.getX() < currentPosition.getX()) {
                neededDirection = IWorld.Direction.LEFT;
            } else if (nextPosition.getY() > currentPosition.getY()) {
                neededDirection = IWorld.Direction.UP;
            }else{
                neededDirection = IWorld.Direction.DOWN;
            }
            while(target.getWorld().getCurrentDirection() != neededDirection){
                command = Command.create("right");
                command.execute(target);
            }
            command = Command.create("forward 1");
            command.execute(target);
            if(path.size() == 0){
                break;
            }
        }
        return true;
    }








    /**
     * > The function takes in a world and returns a list of positions that the robot should travel to in order to reach
     * the edge of the world
     *
     * @param world The world object that contains the current position of the robot.
     * @return A list of positions that the robot should travel to.
     */
    private List<Position> getPath(IWorld world){

        //Positions we are going to travel
        List<Position> toBeExplored = new ArrayList<>();

        //Positions we have travelled.
        List<Position> exploredList = new ArrayList<>();

        //The path the algorithm found.
        Map<Position,Position> path = new LinkedHashMap<>();

        Position start = world.getPosition();
        toBeExplored.add(start);

        exploredList.add(start);

        Position end=start;
        while(!toBeExplored.isEmpty()){
            Position currentPosition = toBeExplored.get(0);
            toBeExplored.remove(0);

            if((getArgument().equals("top") || getArgument().equals("")) && (currentPosition.getY() == TOP_LEFT.getY())){
                end = currentPosition;
                break;
            }else if(getArgument().equals("left") && currentPosition.getX() == TOP_LEFT.getX()){
                end=currentPosition;
                break;
            }else if(getArgument().equals("right") && currentPosition.getX() == BOTTOM_RIGHT.getX()){
                end=currentPosition;
                break;
            }else if(getArgument().equals("bottom") && currentPosition.getY() == BOTTOM_RIGHT.getY()){
                end=currentPosition;
                break;
            }
            Position rightPosition = new Position(currentPosition.getX()+1, currentPosition.getY());
            Position leftPosition = new Position(currentPosition.getX()-1, currentPosition.getY());
            Position upPosition = new Position(currentPosition.getX(), currentPosition.getY()+1);
            Position bottomPosition = new Position(currentPosition.getX(), currentPosition.getY()-1);

            if(world.isNewPositionAllowed(rightPosition) &&!exploredList.contains(rightPosition)){
                path.put(rightPosition, currentPosition);
                toBeExplored.add(rightPosition);
                exploredList.add(rightPosition);
            }

            if(world.isNewPositionAllowed(leftPosition) &&!exploredList.contains(leftPosition)){
                path.put(leftPosition, currentPosition);
                toBeExplored.add(leftPosition);
                exploredList.add(leftPosition);
            }

            if(world.isNewPositionAllowed(upPosition) &&!exploredList.contains(upPosition)){
                path.put(upPosition, currentPosition);
                toBeExplored.add(upPosition);
                exploredList.add(upPosition);
            }

            if(world.isNewPositionAllowed(bottomPosition) &&!exploredList.contains(bottomPosition)){
                path.put(bottomPosition, currentPosition);
                toBeExplored.add(bottomPosition);
                exploredList.add(bottomPosition);
            }
        }

        List<Position> fwdPath = new ArrayList<>();
        Position cell = end;
        while (!cell.equals(start)){
            fwdPath.add(cell);
            cell = path.get(cell);
        }
        return fwdPath;
    }

    /**
     * This function returns the cost of running through a maze.
     *
     * @return The cost of running the maze.
     */
    @Override
    public int getMazeRunCost() {
        return 0;
    }


}
