package se.inte.group5;

import org.junit.BeforeClass;
import org.junit.Test;

public class CreatureTest {
    Creature a;

    @BeforeClass
    public void createCreatures(){
        a = new Creature(13);
    }

    @Test
    public void getCurrentLife_lifeIs13_true(){
        assertEquals(13, a.getCurrentLife());
    }

    @Test
    public void getCurrentLife_lifeIs10_false(){
        assertNotEquals(10, a.getCurrentLife());
    }


}
