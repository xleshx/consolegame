package local.playground.game.engine;

import java.io.Serializable;

/**
 * Item class. If other then weapon type will be supported, should be switched to interface.
 */
public class Item implements Serializable{
    private String name;
    private String description;
    private double power;

    public Item(String name, String description, double power){
        this.name = name;
        this.description = description;
        this.power = power;
    }

    public double getPower() {
        return power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
}
