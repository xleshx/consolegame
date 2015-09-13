package local.playground.game.screen;

import local.playground.game.engine.StoryStep;

import java.util.Collection;



public class LocationScreen extends AbstractScreen{

    public LocationScreen(StoryStep step){
        super(step);
    }

    @Override
    public StringBuilder getView() {
        StringBuilder sb = new StringBuilder();
        sb.append(EMPTY_LINE_SEPARATOR);
        sb.append(step.getLocation().getDescription());
        sb.append(EMPTY_LINE_SEPARATOR);
        sb.append(EMPTY_LINE_SEPARATOR);
        sb = appendMessages(sb, step.getMessages());
        sb.append(EMPTY_LINE_SEPARATOR);
        sb.append(ACTIONS_HEADER);
        sb.append(EMPTY_LINE_SEPARATOR);
        sb = appendActions(sb, step.getActions());
        sb.append(EMPTY_LINE_SEPARATOR);
        return sb;
    }

    private StringBuilder appendMessages(StringBuilder sb, Collection<String> messages){
        messages.forEach((s) -> {
                sb.append(s);
                sb.append(EMPTY_LINE_SEPARATOR);
            }
        );
        return sb;
    }

}
