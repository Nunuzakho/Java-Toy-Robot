package za.co.wethinkcode.toyrobot;


import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void getShutdownName() {
        Command test = new ShutdownCommand();
        assertEquals("off", test.getName());
    }

    @Test
    void executeShutdown() {
        IWorld world = new TextWorld(new EmptyMaze());
        Robot robot = new Robot("CrashTestDummy");
        Command shutdown = Command.create("shutdown");
        assertFalse(shutdown.execute(robot));
        assertEquals("Shutting down...", robot.getStatus());
    }

    @Test
    void getForwardName() {
        ForwardCommand test = new ForwardCommand("100");
        assertEquals("forward", test.getName());
        assertEquals("100", test.getArgument());
    }

    @Test
    void executeForward() {
        IWorld world = new TextWorld(new EmptyMaze());
        Robot robot = new Robot("CrashTestDummy");
        Command forward100 = Command.create("forward 10");
        assertTrue(forward100.execute(robot));
        Position expectedPosition = new Position(IWorld.CENTRE.getX(), IWorld.CENTRE.getY() + 10);
        assertEquals(expectedPosition, robot.getWorld().getPosition());
        assertEquals("Moved forward by 10 steps.", robot.getStatus());
    }

    @Test
    void getHelpName() {
        Command test = new HelpCommand();                                                               //<1>
        assertEquals("help", test.getName());
    }

    @Test
    void executeHelp() {
        IWorld world = new TextWorld(new EmptyMaze());
        Robot robot = new Robot("CrashTestDummy");
        Command help = Command.create("help");
        assertTrue(help.execute(robot));
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
                "MAZERUN RIGHT - robot finds its own pathway to the right, e.g 'mazerun right'",robot.getStatus());
    }

    @Test
    void createCommand() {
        Command forward = Command.create("forward 10");                                                 //<1>
        assertEquals("forward", forward.getName());
        assertEquals("10", forward.getArgument());

        Command shutdown = Command.create("shutdown");                                                  //<2>
        assertEquals("off", shutdown.getName());

        Command help = Command.create("help");                                                          //<3>
        assertEquals("help", help.getName());
    }

    @Test
    void createInvalidCommand() {
        try {
            Command forward = Command.create("say hello");                                              //<4>
            fail("Should have thrown an exception");                                                    //<5>
        } catch (IllegalArgumentException e) {
            assertEquals("Unsupported command: say hello", e.getMessage());                             //<6>
        }
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
