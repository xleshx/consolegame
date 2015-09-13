package local.playground.game.screen;

import local.playground.game.engine.Action;
import local.playground.game.engine.StoryStep;

import java.util.Collection;

/**
 * Abstact Screen holds common appendActions method and some static. Possibly should be switched to aggregation.
 */
public abstract class AbstractScreen implements Screen{

    protected static String ACTIONS_HEADER = "Available actions:";
    protected static String EMPTY_LINE_SEPARATOR = "\n";
    protected StoryStep step;

    public AbstractScreen(StoryStep step) {
        this.step = step;
    }

    protected StringBuilder appendActions(StringBuilder sb, Collection<Action> actions){
        int i = 1;
        for (Action action: actions){
            sb.append(i++ + "." + "  " + action.getDescription());
            sb.append(EMPTY_LINE_SEPARATOR);
        }
        return sb;
    }
}
