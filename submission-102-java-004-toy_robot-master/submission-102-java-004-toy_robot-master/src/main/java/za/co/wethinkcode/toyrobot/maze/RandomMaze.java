package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.List;
import java.util.Random;

import static za.co.wethinkcode.toyrobot.world.IWorld.*;

public class RandomMaze extends BaseMaze {
    Random random = new Random();
    // Creating a random number of obstacles between 0 and 10 and placing them randomly in the world.
    public RandomMaze(){
        for (int i = 0; i < random.nextInt(11); i++) {
            int x = random.nextInt((BOTTOM_RIGHT.getX()+BOTTOM_RIGHT.getX()+1) + TOP_LEFT.getX());
            int y = random.nextInt((TOP_LEFT.getY()+ TOP_LEFT.getY()+1)+ BOTTOM_RIGHT.getY());
            getObstacles().add(new SquareObstacle(x,y));
        }
    }
}
