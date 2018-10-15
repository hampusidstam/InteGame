package se.inte.group5;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CreatureTest {
    Creature a;

    @Before
    public void createCreatures(){
        a = new Creature(13, 20);
    }

    @Test
    public void getCurrentLife_lifeIs13_true(){
        assertEquals(13, a.getCurrentLife());
    }

    @Test
    public void getCurrentLife_lifeIs10_false(){
        assertNotEquals(10, a.getCurrentLife());
    }

    @Test
    public void getMaxSpeed_MaxSpeedIs20_true(){
        assertEquals(20, a.getMaxSpeed());
    }

    @Test
    public void getMaxSpeed_MaxSpeedIs10_false(){
        assertNotEquals(10, a.getMaxSpeed());
    }
}
