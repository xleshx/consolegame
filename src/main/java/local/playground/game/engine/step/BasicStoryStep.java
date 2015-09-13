package local.playground.game.engine.step;

import local.playground.game.engine.*;
import local.playground.game.engine.Character;
import local.playground.game.engine.action.fight.GoFightAction;

import java.lang.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Basic Story step implementation.
 * Aggregates location, actions and messages.
 */
public class BasicStoryStep implements StoryStep {

    private static String ENTITY_MESSAGE_TPL = "You see %s in front of you. He looks %s";
    private Location location;
    private ArrayList<Action> actions = new ArrayList<>();
    private ArrayList<String> messages = new ArrayList<>();

    public BasicStoryStep(Location location) {
        this.location = location;
        updateState();
    }

    public void updateState() {
        this.actions.clear();
        this.messages.clear();
        this.location.getEntities().stream()
            .filter(e -> e.getState() != Entity.State.DEAD)
            .forEach((entity) -> {
                messages.add(String.format(ENTITY_MESSAGE_TPL,
                        entity.getName(),
                        entity.getState() == Entity.State.AGGRESSIVE ? "aggressive" : "passive")
                );
                this.actions.add(new GoFightAction("Attack", "Attack " + entity.getName(), entity));
            });

    }

    /**
     * Delegates call to an Action.
     *
     * @param action
     * @param character
     * @return next or updated current StoryStep
     */
    @Override
    public StoryStep process(Action action, Character character) {
        return action.doIt(this, character);
    }


    @Override
    public Action getActionByOrderId(int actionId) {
        if (actionId < 0 || actionId > actions.size()){
             return null;
        }
        return actions.get(actionId - 1);
    }


    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Collection<Action> getActions() {
        return actions;
    }

    @Override
    public ArrayList<String> getMessages() {
        return messages;
    }
}
