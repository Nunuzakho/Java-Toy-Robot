package za.co.wethinkcode.toyrobot.maze;


import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.List;

public class SimpleMaze extends BaseMaze {

    // Adding a new obstacle to the maze.
    public SimpleMaze() {
        getObstacles().add(new SquareObstacle(1, 1));

    }

}