package local.playground.game.engine.action.fight;

import local.playground.game.engine.Entity;
import local.playground.game.engine.step.BasicStoryStep;
import local.playground.game.engine.Item;
import local.playground.game.engine.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;

/**
 * Created by lesh on 13.09.15.
 */
public class AttackWithActionTest {
    Item item;
    char separator = ((DecimalFormat) DecimalFormat.getInstance()).getDecimalFormatSymbols().getDecimalSeparator();

    String result;
    @Before
    public void setup(){
        item = new Item("TestGun","",0.5d);
        result = "Attack with TestGun (power 0" + separator + "5)";

    }
    @Test
    public void testConstructor(){

        AttackWithAction action = new AttackWithAction(item, new Entity("", 100, Entity.State.AGGRESSIVE, item),
            new BasicStoryStep(new Location()));
        Assert.assertTrue("Action description format is wrong", action.getDescription().equals(result));
    }
}
