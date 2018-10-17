package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WallTest {

    Wall w;

    @Before
    public void createWall() {
        w = new Wall(Color.GRAY);
    }

    @Test
    public void getSymbol_symbolIsX_true() {
        assertEquals('X', w.getSymbol());
    }

    @Test
    public void getColor_colorIsGray_true() {
        assertEquals(Color.GRAY, w.getColor());
    }

}
