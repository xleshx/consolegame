package local.playground.game.engine.action.start;

import local.playground.game.engine.*;
import local.playground.game.engine.action.AbstractAction;

/**
 * Created by aantipov on 10.09.2015.
 */
public class SaveGameAction extends AbstractAction {

    private Engine engine;

    public SaveGameAction(String name, String description, Engine engine){
        super(name, description);
        this.engine = engine;

    }
    @Override
    public StoryStep doIt(StoryStep item, local.playground.game.engine.Character character) {
        this.engine.save();
        item.getMessages().add("The game saved successfully");
        return item;
    }
}
