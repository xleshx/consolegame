package local.playground.game.screen;

import local.playground.game.engine.StoryStep;

/**
 * Start screen with default commads like new game, load game, save game.
 */
public class StartScreen extends AbstractScreen{

    private static String BANNER = "  __                         ()                 \n" +
        "  /  `            _/_         /\\                 \n" +
        " /--  ______  _   /  __  ,   /  )  _   __.  _. _ \n" +
        "(___,/ / / <_/_)_<__/ (_/_  /__/__/_)_(_/|_(__</_\n" +
        "            /          /         /               \n" +
        "           '          '         '                ";


    public StartScreen(StoryStep step) {
        super(step);
    }

    @Override
    public StringBuilder getView() {
        StringBuilder sb = new StringBuilder();
        sb.append(BANNER);
        sb.append(EMPTY_LINE_SEPARATOR);
        sb.append(ACTIONS_HEADER);
        sb.append(EMPTY_LINE_SEPARATOR);
        sb = appendActions(sb, step.getActions());
        sb.append(EMPTY_LINE_SEPARATOR);
        return sb;
    }
}
