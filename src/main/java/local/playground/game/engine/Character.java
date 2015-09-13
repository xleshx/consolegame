package local.playground.game.engine;

/**
 * Character entity.
 */
public class Character extends Entity{
    private long experience = 0;

    public Character(String name, long health, Entity.State state, Item item) {
        super(name, health, state, item);
    }


    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }
}
