package local.playground.game;

import local.playground.game.screen.Screen;

/**
 * Console abstraction.
 */
public interface Console {
    /**
     * Renders screen.
     * @param screen
     */
    void render(Screen screen);

    /**
     * Read int action code from the input.
     * @return
     */
    int readActionInput();

    /**
     * Read additional int code from the input.
     * @return
     */
    int readAdditionalCode();

    /**
     * Read additional data from the input.
     * @return
     */
    String readAdditionalData();

}
