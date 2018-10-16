package se.inte.group5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MapTest {

    private Map map = new Map(200, 100);

    @Test
    public void shouldReturnWidthOfMap() {
        assertEquals(200, map.getWidth());
    }

    @Test
    public void shouldReturnHeightOfMap() {
        assertEquals(100, map.getHeight());
    }
}
