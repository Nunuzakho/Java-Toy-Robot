package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

public class SquareObstacle implements Obstacle {

   private final int y;
   private final int x;



    private final int size;

    // This is a constructor that calls another constructor with the same name but with different parameters.
    public SquareObstacle(int x, int y){
        this(x,y,5);
    }


    // This is a constructor that calls another constructor with the same name but with different parameters.
    public SquareObstacle(int x, int y, int size){
        this.y = y;
        this.x = x;
        this.size = size;
    }
    /**
     * `getBottomLeftX` returns the x coordinate of the bottom left corner of the rectangle
     *
     * @return The x coordinate of the bottom left corner of the rectangle.
     */
    @Override
    public int getBottomLeftX() {
        return x;
    }

    /**
     * Returns the y coordinate of the bottom left corner of the rectangle.
     *
     * @return The y coordinate of the bottom left corner of the rectangle.
     */
    @Override
    public int getBottomLeftY() {
        return y;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return The size of the array.
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * > If the position is within the bounds of the block, then it is blocked
     *
     * @param position The position to check
     * @return A boolean value.
     */
    @Override
    public boolean blocksPosition(Position position) {

          boolean checkY = y <= position.getY() && (y + (size-1)) >= position.getY();
          boolean checkX = x <= position.getX() && (x + (size-1)) >= position.getX();


        return checkX && checkY;
    }

    /**
     * If the positions are on the same row, check if any of the positions between them are blocked. If the positions are
     * on the same column, check if any of the positions between them are blocked
     *
     * @param a The starting position of the path
     * @param b the position of the block
     * @return A boolean value.
     */
    @Override
    public boolean blocksPath(Position a, Position b) {
        int aX = a.getX();
        int aY = a.getY();
        int bX = b.getX();
        int bY = b.getY();

        if(aX==bX){
            if(aY<bY){
                for (int i = aY; i <= bY; i++) {
                    if (blocksPosition(new Position(aX, i))){
                        return true;
                    }
                }
            }else{
                for (int i = aY; i >= bY; i--) {
                    if (blocksPosition(new Position(aX, i))){
                        return true;
                    }
                }
            }
        }else if(aY==bY){
            if(aX<bX){
                for (int i = aX; i <= bX ; i++) {
                    if (blocksPosition(new Position(i,aY))){
                        return true;
                    }
                }
            }else {
                for (int i = aX; i >= bX ; i--) {
                    if (blocksPosition(new Position(i,aY))){
                        return true;
                    }
                }
            }

        }
        return false;

    }
}
