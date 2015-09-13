package local.playground.game.engine.action.move;

import local.playground.game.engine.*;
import local.playground.game.engine.Character;
import local.playground.game.engine.action.AbstractAction;

/**
 * Basic move action, returns step to move on in doIt method.
 */
public class MoveAction extends AbstractAction{
    private StoryStep itemMoveTo;

    public MoveAction(String name, String description, StoryStep itemMoveTo) {
        super(name, description);
        this.itemMoveTo = itemMoveTo;
    }

    @Override
    public StoryStep doIt(StoryStep item, Character character) {
        return itemMoveTo;
    }

}
