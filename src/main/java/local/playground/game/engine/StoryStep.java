package local.playground.game.engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Story Step interface.
 */
public interface StoryStep extends Serializable {
    /**
     * Called from within main game loop in the Engine.
     * @param action
     * @param character
     * @return
     */
    StoryStep process(Action action, Character character);

    Action getActionByOrderId(int actionId);

    Location getLocation();

    Collection<Action> getActions();

    ArrayList<String> getMessages();

    void updateState();
}
