package local.playground.game.engine.action;

import local.playground.game.engine.Action;

/**
 * Base abstract action, to save typing time.
 */
public abstract class AbstractAction implements Action {

    private final String name;
    private final String description;

    protected AbstractAction(String name, String description) {
        this.name = name;
        this.description = description;
    }


    @Override
    public boolean isNeedAdditionalCode() {
        return false;
    }

    @Override
    public boolean isNeedAdditionalData() {
        return false;
    }

    @Override
    public void setAdditionalCode(int code) {}

    @Override
    public void setAdditionalData(String data) {}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
