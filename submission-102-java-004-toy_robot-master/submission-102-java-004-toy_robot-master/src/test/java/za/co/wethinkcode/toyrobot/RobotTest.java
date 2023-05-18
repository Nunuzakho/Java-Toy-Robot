package za.co.wethinkcode.toyrobot;


import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    @Test
    void initialPosition() {
        Robot robot = new Robot("CrashTestDummy");
        assertEquals(IWorld.CENTRE, robot.getWorld().getPosition());
        assertEquals(IWorld.Direction.UP, robot.getWorld().getCurrentDirection());
    }

    @Test
    void dump() {
        Robot robot = new Robot("CrashTestDummy");
        assertEquals("[0,0] CrashTestDummy> Ready", robot.toString());
    }

    @Test
    void shutdown() {
        IWorld world = new TextWorld(new EmptyMaze());
        Robot robot = new Robot("CrashTestDummy");
        ShutdownCommand command = new ShutdownCommand();
        assertFalse(robot.handleCommand(command));
    }

    @Test
    void forward() {
        IWorld world = new TextWorld(new EmptyMaze());
        Robot robot = new Robot("CrashTestDummy");
        ForwardCommand command = new ForwardCommand("10");
        assertTrue(robot.handleCommand(command));
        Position expectedPosition = new Position(IWorld.CENTRE.getX(), IWorld.CENTRE.getY() + 10);
        assertEquals(expectedPosition, robot.getWorld().getPosition());
        assertEquals("Moved forward by 10 steps.", robot.getStatus());
    }

    @Test
    void forwardforward() {
        IWorld world = new TextWorld(new EmptyMaze());
        Robot robot = new Robot("CrashTestDummy");
        assertTrue(robot.handleCommand(new ForwardCommand("10")));
        assertTrue(robot.handleCommand(new ForwardCommand("5")));
        assertEquals("Moved forward by 5 steps.", robot.getStatus());
    }

    @Test
    void tooFarForward() {
        IWorld world = new TextWorld(new EmptyMaze());
        Robot robot = new Robot("CrashTestDummy");
        assertTrue(robot.handleCommand(new ForwardCommand("1000")));
        assertEquals(IWorld.CENTRE, robot.getWorld().getPosition());
        assertEquals("Sorry, I cannot go outside my safe zone.", robot.getStatus());
    }

    @Test
    void help() {
        IWorld world = new TextWorld(new EmptyMaze());
        Robot robot = new Robot("CrashTestDummy");
        Command command = new HelpCommand();
        assertTrue(robot.handleCommand(command));
        assertEquals("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n" +
                "BACK - move back by specified number of steps, e.g. 'BACK 10'\n" +
                "LEFT - robot turns left, e.g 'left'\n" +
                "RIGHT - robot turns right, e.g 'right'\n" +
                "SPRINT - robot sprints forward by a specified number of steps, e.g 'SPRINT 10'\n" +
                "MAZERUN - robot mazeruns through a maze by finding the nearest pathway defaulting to the top, e.g 'mazerun'\n" +
                "MAZERUN TOP - robot finds its own pathway to the top, e.g 'mazerun top'\n" +
                "MAZERUN  BOTTOM - robot finds its own pathway to the bottom, e.g 'mazerun bottom'\n" +
                "MAZERUN LEFT - robot finds its own pathway to the left, e.g 'mazerun left'\n" +
                "MAZERUN RIGHT - robot finds its own pathway to the right, e.g 'mazerun right'", robot.getStatus());
    }
    @Test
    void leftCommand(){
        IWorld world = new TextWorld(new EmptyMaze());
        Robot robot = new Robot("CrashTestDummy");
        Command left = Command.create("left");
        assertTrue(left.execute(robot));
        assertEquals(IWorld.Direction.LEFT, robot.getWorld().getCurrentDirection());
    }
    @Test
    void rightCommand(){
        IWorld world = new TextWorld(new EmptyMaze());
        Robot robot = new Robot("CrashTestDummy");
        Command right = Command.create("right");
        assertTrue(right.execute(robot));
        assertEquals(IWorld.Direction.RIGHT, robot.getWorld().getCurrentDirection());
    }
    @Test
    void sprintCommand(){
        IWorld world = new TextWorld(new EmptyMaze());
        Robot robot = new Robot("CrashTestDummy");
        Command command = Command.create("sprint 5");
        assertTrue(command.execute(robot));
        Position expectedPosition = new Position(IWorld.CENTRE.getX(), IWorld.CENTRE.getY() + 15);
        assertEquals(expectedPosition, robot.getWorld().getPosition());
        assertEquals("Moved forward by 1 steps.", robot.getStatus());

    }
}