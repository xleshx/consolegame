package local.playground.game.engine.action.fight;

import local.playground.game.engine.*;
import local.playground.game.engine.Character;
import local.playground.game.engine.action.AbstractAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Attack Action executes fight logic, updates messages with battle log to show to Player.
 */
public class AttackWithAction extends AbstractAction {

    private StoryStep exitStep;
    private Entity opponent;

    private static String CHARACTER_ATTACK_MSG_TPL = "You hit %s with %s. Health before hit: %d hit: %.1f health after: %d";
    private static String OPPONENT_ATTACK_MSG_TPL = "%s hit you with %s. Health before hit: %d hit: %.1f health after: %d";
    private static String WIN_MSG_TPL = "%s has been defeated. You experience now %d";
    private static String LOSE_MSG_TPL = "You have been defeated by %s";
    private static String ATTACK_ACTION_MSG_TPL = "Attack with %s (power %.1f)";

    public AttackWithAction(Item item, Entity opponent, StoryStep exitStep){
        super("Attack with item", String.format(ATTACK_ACTION_MSG_TPL, item.getName(), item.getPower()));
        this.exitStep = exitStep;
        this.opponent = opponent;

    }
    @Override
    public StoryStep doIt(StoryStep step, Character character) {

        boolean stopIt = false;
        long hitCount = 0;
        long initialOpponentHealt = opponent.getHealth();
        Collection<String> messages = new ArrayList<>();

        while(!stopIt) {
            Entity.BattleLog battleLog1 = character.attack(opponent);
            messages.add(createBattleMessage(CHARACTER_ATTACK_MSG_TPL, opponent.getName(), battleLog1));
            if (opponent.getState() == Entity.State.DEAD){
                updateExperience(character, initialOpponentHealt, hitCount);
                messages.add(String.format(WIN_MSG_TPL, opponent.getName(), character.getExperience()));
                stopIt = true;
                hitCount++;
                continue;
            }
            Entity.BattleLog battleLog2 = opponent.attack(character);
            messages.add(createBattleMessage(OPPONENT_ATTACK_MSG_TPL, opponent.getName(), battleLog2));
            if (character.getState() == Entity.State.DEAD){
                messages.add(String.format(LOSE_MSG_TPL, opponent.getName()));
                stopIt = true;
            }
        }

        return updateExitStory(exitStep, messages);
    }

    private StoryStep updateExitStory(StoryStep story, Collection<String> messages){
        Collection<Action> tmp = story.getActions().stream()
            .filter(action -> !(action instanceof GoFightAction))
            .collect(Collectors.toList());
        story.updateState();
        messages.forEach(m -> story.getMessages().add(m));
        tmp.forEach(action -> story.getActions().add(action));

        return story;
    }

    private void updateExperience(Character character, long initialOpponentHealth, long hitCount){
        long expIncrease = initialOpponentHealth + hitCount;
        long exp = character.getExperience();
        character.setExperience(exp+expIncrease);
    }


    private String createBattleMessage(String format, String entityName, Entity.BattleLog log){
        return String.format(format,
            entityName,
            log.getAttackWith(),
            log.getHealthBeforeHit(),
            log.getHit(),
            log.getHealthAfterHit());
    }


}

