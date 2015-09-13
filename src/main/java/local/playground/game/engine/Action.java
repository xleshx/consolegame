package local.playground.game.engine;

import java.io.Serializable;
import java.lang.*;

/**
 * Action interface.
 */
public interface Action extends Serializable{
    /**
     * Process the action in context of current StoryStep and Character.
     * @param item
     * @param character
     * @return next or updated current StoryStep
     */
    StoryStep doIt(StoryStep item, Character character);

    String getName();

    String getDescription();

    boolean isNeedAdditionalCode();
    boolean isNeedAdditionalData();

    void setAdditionalCode(int code);
    void setAdditionalData(String data);
}
