package local.playground.game.engine;

import java.io.Serializable;

/**
 * Basic Entity class.
 */
public class Entity implements Serializable{

    private State state;
    private Item item;
    private long health = 100;
    private String name;

    public enum State {
        DEAD,
        AGGRESSIVE,
        NEUTRAL
    }

    public class BattleLog{
        private String attackWith;
        private double hit;
        private long healthBeforeHit;
        private long healthAfterHit;

        public BattleLog(String attackWith, double hit, long healthBeforeHit, long healthAfterHit){
            this.attackWith = attackWith;
            this.hit = hit;
            this.healthBeforeHit = healthBeforeHit;
            this.healthAfterHit = healthAfterHit;
        }

        public String getAttackWith() {
            return attackWith;
        }

        public double getHit() {
            return hit;
        }

        public long getHealthBeforeHit() {
            return healthBeforeHit;
        }

        public long getHealthAfterHit() {
            return healthAfterHit;
        }
    }

    public Entity(String name, long health, State state, Item item){
        this.name = name;
        this.health = health;
        this.state = state;
        this.item = item;
    }


    public BattleLog attack(Entity entity){
        return attackWith(entity, this.item);
    }


    /**
     *  Should be made public when carry of multiple items will be implemented.
     * @param entity
     * @param item
     */
    private BattleLog attackWith(Entity entity, Item item){
        double rnd = Math.random();
        double hit = rnd * health * item.getPower();
        long lHit = Math.round(hit);
        long healthBefore = entity.getHealth();
        entity.getHit(lHit);
        return new BattleLog(item.getName(), lHit, healthBefore, entity.getHealth());
    }

    public State getState() {
        return state;
    }

    public long getHit(long hit){
        this.health = this.health - hit;
        if (this.health <= 0){
            this.state = State.DEAD;
        }
        return this.health;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Item getItem() {
        return item;
    }

    public long getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }



}
