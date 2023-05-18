package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseMaze implements Maze{
    private final List<Obstacle> obstacleList = new ArrayList<>();

    @Override
    public boolean blocksPath(Position a, Position b){
        for(Obstacle obstacle:getObstacles()){
            if(obstacle.blocksPath(a,b)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Obstacle> getObstacles() {
        return obstacleList;
    }

    public boolean blocksPosition(Position position){
        for(Obstacle obstacle:getObstacles()){
            if(obstacle.blocksPosition(position)){
                return true;
            }
        }
        return false;
    }
}