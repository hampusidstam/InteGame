package se.inte.group5;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class LevelTest {

    private Level level = new Level(200,100);

    @Test
    public void shouldReturnWidthOfLevel()
    {
        assertEquals( level.getWidth() , 200);
    }

    @Test
    public void shouldReturnHeightOfLevel()
    {
        assertEquals( level.getHeight() , 100);
    }

}
