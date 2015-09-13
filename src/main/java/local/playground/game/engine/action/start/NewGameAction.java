package local.playground.game.engine.action.start;

import local.playground.game.engine.StoryStep;
import local.playground.game.engine.action.AbstractAction;

/**
 * Created by lesh on 06.09.15.
 */
public class NewGameAction extends AbstractAction {

    private StoryStep itemMoveTo;

    public NewGameAction(String name, String description, StoryStep item) {
        super(name, description);
        this.itemMoveTo = item;
    }

    @Override
    public StoryStep doIt(StoryStep item, local.playground.game.engine.Character character) {
        return itemMoveTo;
    }
}
