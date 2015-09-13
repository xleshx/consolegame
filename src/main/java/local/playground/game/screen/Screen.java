package local.playground.game.screen;

/**
 * Shell screen abstraction. Holds commands avalable for this screen.
 */
public interface Screen {

    /**
     * Form a view based on banner data and command names (sort of model for a view).
     * @return View that represent a screen to render
     */
    StringBuilder getView();

}
