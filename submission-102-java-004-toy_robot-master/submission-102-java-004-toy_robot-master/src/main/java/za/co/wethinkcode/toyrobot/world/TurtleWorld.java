package za.co.wethinkcode.toyrobot.world;

import org.turtle.Turtle;
import za.co.wethinkcode.toyrobot.maze.Maze;

import java.awt.*;

public class TurtleWorld extends AbstractWorld {
    private final Turtle shirleyTurtle;

    // This is the constructor for the TurtleWorld class. It is calling the constructor of the super class (AbstractWorld)
    // and then creating a new turtle object.
    public TurtleWorld(Maze maze){
        super(maze);
        shirleyTurtle = new Turtle(0.5,0.5,90);
        shirleyTurtle.show();
    }
    /**
     * If the turtle is turning right, turn the ShirleyTurtle right 90 degrees, otherwise turn the ShirleyTurtle left 90
     * degrees
     *
     * @param turnRight a boolean that is true if the turtle is turning right, false if it is turning left
     */
    public void updateDirection(boolean turnRight) {
        super.updateDirection(turnRight);
        if(turnRight){
            shirleyTurtle.right(90);
        }else{
            shirleyTurtle.left(90);
        }
    }
    /**
     * The function `updatePosition` is overridden to update the position of the turtle in the ShirleyTurtle class
     *
     * @param nrSteps The number of steps the turtle should take.
     * @return The response of the updatePosition method in the super class.
     */
    @Override
    public UpdateResponse updatePosition(int nrSteps){
        UpdateResponse response = super.updatePosition(nrSteps);

        if(response == UpdateResponse.SUCCESS){
            if(getCurrentDirection().equals(Direction.UP) || getCurrentDirection().equals(Direction.DOWN)){
                shirleyTurtle.forward(nrSteps/getyScale());
            }else {
                shirleyTurtle.forward(nrSteps/getxScale());
            }
        }
        return response;
    }

    /**
     * This function draws a red square for each obstacle in the environment
     */
    @Override
    public void showObstacles() {
        for (Obstacle obstacle: getObstacles()) {
            Turtle draw = new Turtle(obstacle.getBottomLeftX()/getxScale()+0.5, obstacle.getBottomLeftY()/getyScale()+0.5,90);
            draw.setColor(Color.red);
            for (int i = 0; i < 4; i++) {
                draw.forward(obstacle.getSize()/getyScale());
                draw.right(90);
                draw.forward(obstacle.getSize()/getxScale());
                draw.right(90);
            }
        }
    }
}
