package za.co.wethinkcode.toyrobot.world;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.Maze;

import java.util.List;




public abstract class AbstractWorld implements IWorld{
 public Direction currentDirection;
 private Position position;

 protected final Maze maze;
 private final double xScale;
 private final double yScale;


 public AbstractWorld(Maze maze){
  this.position = IWorld.CENTRE;
  this.currentDirection=Direction.UP;
  this.maze = maze;
  xScale = (BOTTOM_RIGHT.getX() - TOP_LEFT.getX());
  yScale = (TOP_LEFT.getY() - BOTTOM_RIGHT.getY());
 }

 public double getxScale() {
  return xScale;
 }

 public double getyScale() {
  return yScale;
 }

 public Position getPosition() {
  return this.position;
 }

 public void setCurrentDirection(Direction currentDirection) {
  this.currentDirection = currentDirection;
 }

 public void setPosition(Position position) {
  this.position = position;
 }


 @Override
 public UpdateResponse updatePosition(int nrSteps){
  int newX = getPosition().getX();
  int newY = getPosition().getY();

  if (Direction.UP.equals(this.currentDirection)) {
   newY = newY + nrSteps;
  }else if(Direction.RIGHT.equals(this.currentDirection)){
   newX = newX + nrSteps;
  }else if(Direction.DOWN.equals(this.currentDirection)){
   newY = newY - nrSteps;
  }else if(Direction.LEFT.equals(this.currentDirection)){
   newX = newX - nrSteps;
  }


  Position newPosition = new Position(newX, newY);
  if(isPositionBlocked(newPosition, position)){
   return UpdateResponse.FAILED_OBSTRUCTED;
  }

  if (newPosition.isIn(TOP_LEFT,BOTTOM_RIGHT)){
       setPosition(newPosition);
       return UpdateResponse.SUCCESS;
  }
       return UpdateResponse.FAILED_OUTSIDE_WORLD;
 }

 @Override
 public void updateDirection(boolean turnRight) {
  if(turnRight){
   switch (currentDirection){
    case UP:
     setCurrentDirection(Direction.RIGHT);
     break;
    case RIGHT:
     setCurrentDirection(Direction.DOWN);
     break;
    case DOWN:
     setCurrentDirection(Direction.LEFT);
     break;
    case LEFT:
     setCurrentDirection(Direction.UP);
     break;
   }
  }else{
   switch (currentDirection){
    case UP:
     setCurrentDirection(Direction.LEFT);
     break;
    case LEFT:
     setCurrentDirection(Direction.DOWN);
     break;
    case DOWN:
     setCurrentDirection(Direction.RIGHT);
     break;
    case RIGHT:
     setCurrentDirection(Direction.UP);
     break;
   }
  }
 }

 @Override
 public Direction getCurrentDirection() {
  return currentDirection;
 }

 @Override
 public boolean isNewPositionAllowed(Position position) {
  return position.isIn(TOP_LEFT,BOTTOM_RIGHT) && !maze.blocksPath(position,position);
 }

 @Override
 public boolean isAtEdge() {
 if(position.getY() == 200){
  return true;
 }else {
  return false;
 }
 }

 @Override
 public void reset() {
 position = CENTRE;
 currentDirection = Direction.UP;
 }


public boolean isPositionBlocked(Position newPosition, Position currentPosition){
  for(Obstacle obstacle: maze.getObstacles()){
   if(obstacle.blocksPosition(newPosition)){
      return true;
   }else if(obstacle.blocksPath(currentPosition, newPosition)){
    return true;
   }
  }

  return false;
}
 @Override
 public List<Obstacle> getObstacles() {
  return maze.getObstacles();
 }




}
