package local.playground.game.engine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lesh on 08.09.15.
 */
public class EntityTest {
    private Entity entity1;
    private Entity entity2;

    @Before
    public void init(){

        Item item = new Item("","",0.5d);
        entity1 = new Entity("", 100, Entity.State.AGGRESSIVE, item);

        Item item2 = new Item("","",0.2d);
        entity2 = new Entity("", 30, Entity.State.AGGRESSIVE, item2);

    }

    @Test(timeout = 1000)
    public void testBeatup(){
        int i = 0;
        while (entity2.getHealth() > 0){
            entity1.attack(entity2);
            i++;
        }
        Assert.assertTrue("Entity2 is not dead after " + i + " attacks", entity2.getHealth() <= 0);
    }

    @Test(timeout = 1000)
    public void testFight(){
        int i = 0;
        do {
            entity1.attack(entity2);
            entity2.attack(entity1);
            i++;
        } while (entity1.getHealth() > 0 && entity2.getHealth() > 0);
        Assert.assertTrue("Entity2 is not dead after " + i + " attacks", entity2.getHealth() <= 0);
    }


}
