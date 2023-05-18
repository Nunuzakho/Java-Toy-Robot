package za.co.wethinkcode.toyrobot.maze;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.Position;

import static org.junit.jupiter.api.Assertions.*;

class SimpleMazeRunnerTest {
    @Test
    public void shouldKnowXandY() {
        Position p = new Position(10, 20);
        assertEquals(10, p.getX());
        assertEquals(20, p.getY());

    }
}