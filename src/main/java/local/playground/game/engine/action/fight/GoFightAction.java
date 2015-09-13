package local.playground.game.engine.action.fight;

import local.playground.game.engine.*;
import local.playground.game.engine.Character;
import local.playground.game.engine.action.AbstractAction;
import local.playground.game.engine.step.CombatStep;

/**
 * Fight transition action.
 */
public class GoFightAction extends AbstractAction{
    private Entity opponent;

    public GoFightAction(String name, String description, Entity opponent) {
        super(name, description);
        this.opponent = opponent;
    }

    @Override
    public StoryStep doIt(StoryStep step, Character character) {
        return new CombatStep(step.getLocation(), character, opponent, step);
    }
}
