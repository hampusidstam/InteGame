package se.inte.group5;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class MapTest {

    private Map map = new Map(200,100);

    @Test
    public void shouldReturnWidthOfLevel()
    {
        assertEquals( 200, map.getWidth() );
    }

    @Test
    public void shouldReturnHeightOfLevel()
    {
        assertEquals( 100, map.getHeight());
    }

}
