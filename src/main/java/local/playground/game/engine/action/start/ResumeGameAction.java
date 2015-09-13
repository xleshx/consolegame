package local.playground.game.engine.action.start;

import local.playground.game.engine.Engine;
import local.playground.game.engine.StoryStep;
import local.playground.game.engine.action.AbstractAction;

import java.io.IOException;

/**
 * Created by lesh on 06.09.15.
 */
public class ResumeGameAction extends AbstractAction {
    private Engine engine;

    public ResumeGameAction(String name, String description, Engine engine) {
        super(name, description);
        this.engine = engine;
    }

    @Override
    public StoryStep doIt(StoryStep item, local.playground.game.engine.Character character) {
        try {
            item = engine.load();
        } catch (IOException e) {
            item.getMessages().add("Error occured during game state loading, please try again");
        }
        return item;
    }

}
