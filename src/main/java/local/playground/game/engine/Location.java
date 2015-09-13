package local.playground.game.engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Location abstraction. Aggregates entities and items within.
 */
public class Location implements Serializable{
    private String name;
    private String description;
    private Collection<Entity> entities = new ArrayList<>();
    private Collection<Entity> items = new ArrayList<>();

    public Collection<Entity> getEntities() {
        return entities;
    }

    public Location setName(String name){
        this.name = name;
        return this;
    }

    public Location setDescription(String description) {
        this.description = description;
        return this;
    }

    public Collection<Entity> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
