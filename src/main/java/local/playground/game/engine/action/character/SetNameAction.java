package local.playground.game.engine.action.character;

import local.playground.game.engine.*;
import local.playground.game.engine.Character;
import local.playground.game.engine.action.AbstractAction;

import java.util.Collection;

/**
 * Set character name action.
 */
public class SetNameAction extends AbstractAction{

    private static String NAME_MESSAGE_FORMAT = "Your characters name is %s";
    private static String HEALTH_MESSAGE_FORMAT = "Your characters health is %d";
    private static String NAME_EXPERIENCE_FORMAT = "Your characters experience is %d";
    private String additionalData;

    public SetNameAction(String name, String description) {
        super(name, description);
    }

    @Override
    public StoryStep doIt(StoryStep item, Character character) {
        String name = this.additionalData;
        character.setName(name);
        updateMessages(item.getMessages(), character);
        return item;
    }

    private void updateMessages(Collection<String> messages, Character character){
        messages.clear();
        messages.add(String.format(NAME_MESSAGE_FORMAT, character.getName()));
        messages.add(String.format(HEALTH_MESSAGE_FORMAT, character.getHealth()));
        messages.add(String.format(NAME_EXPERIENCE_FORMAT, character.getExperience()));
    }

    @Override
    public boolean isNeedAdditionalData() {
        return true;
    }

    @Override
    public void setAdditionalData(String data) {
        this.additionalData = data;

    }
}
