package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PositionTest {
    Position pos1;

    @Before
    public void createPositions(){
        pos1 = new Position(5, 13);
    }

    @Test
    public void getX_xIs5_true(){
        assertEquals(5, pos1.getX());
    }

    @Test
    public void getY_yIs13_true(){
        assertEquals(13, pos1.getY());
    }
}
