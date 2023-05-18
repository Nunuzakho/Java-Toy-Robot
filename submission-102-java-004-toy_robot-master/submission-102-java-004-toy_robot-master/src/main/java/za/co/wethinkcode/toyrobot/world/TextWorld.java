package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.maze.Maze;

import java.util.List;

public class TextWorld extends AbstractWorld {

    // Calling the constructor of the parent class.
    public TextWorld(Maze maze) {
        super(maze);
    }


    /**
     * > This function returns a list of all the obstacles in the maze
     *
     * @return The list of obstacles in the maze.
     */
    @Override
    public List<Obstacle> getObstacles() {
        return maze.getObstacles();
    }



    /**
     * Prints out the obstacles in the maze.
     */
    @Override
    public void showObstacles() {
        System.out.println("There are some obstacles: ");
        for(Obstacle obstacle:maze.getObstacles()){
            System.out.println("- At position "+obstacle.getBottomLeftX()+","+obstacle.getBottomLeftY()+" (to "+(obstacle.getBottomLeftX()+3)+","+(obstacle.getBottomLeftY()+4)+")");
        }
    }
}
