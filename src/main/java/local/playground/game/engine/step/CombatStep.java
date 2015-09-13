package local.playground.game.engine.step;

import local.playground.game.engine.*;
import local.playground.game.engine.Character;
import local.playground.game.engine.action.fight.AttackWithAction;


/**
 * Combat story adds AttackWith Action to actions list.
 */
public class CombatStep extends BasicStoryStep {

    public CombatStep(Location location, Character character, Entity opponent, StoryStep exitStep){
        super(location);
        this.getActions().clear();
        this.getActions().add(new AttackWithAction(character.getItem(), opponent, exitStep));
    }

}
