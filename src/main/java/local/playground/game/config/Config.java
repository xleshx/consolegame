package local.playground.game.config;

import local.playground.game.engine.*;
import local.playground.game.engine.Character;
import local.playground.game.engine.action.AbstractAction;
import local.playground.game.engine.action.character.SetNameAction;
import local.playground.game.engine.action.move.MoveAction;
import local.playground.game.engine.action.start.NewGameAction;
import local.playground.game.engine.action.start.ResumeGameAction;
import local.playground.game.engine.action.start.SaveGameAction;
import local.playground.game.engine.step.BasicStoryStep;

import java.io.Serializable;


/**
 * Config object. Holds story state transitions.
 */
public class Config implements Serializable{

    private Engine engine;

    public Config(Engine engine) {
        this.engine = engine;
    }


    public StoryStep getInitialStoryItem() {

        StoryStep storyStep = new BasicStoryStep(new Location());

        storyStep.getActions().add(new NewGameAction("New game", "New game", getCreateCharacterItem()));
        storyStep.getActions().add(new ResumeGameAction("Resume Game", "Resume Game", this.engine));

        return storyStep;
    }

    public StoryStep getCreateCharacterItem() {

        Location location = new Location()
                    .setName("Create character")
                    .setDescription(AsciiArtData.CHARACTER +
                            "Please create your character\n\n");
        StoryStep storyStep = new BasicStoryStep(location);

        storyStep.getActions().add(new SetNameAction("Set name", "Set character's name"));
        storyStep.getActions().add(new MoveAction("Finish", "Finish config", getBaseStoryItem()));

        return storyStep;
    }


    public StoryStep getBaseStoryItem() {

        Location location = new Location()
            .setName("Empty Space")
            .setDescription(
                AsciiArtData.BASE_STEP +
                    "You are alone floating in the empty space. " +
                    "You hear your breath. You see huge spaceship in front of you. \n" +
                    "Oxygen indicator is on the low.");

        StoryStep storyStep = new BasicStoryStep(location);

        storyStep.getActions().add(
            new MoveAction("To the ship", "Activate engine and move to the ship direction",getAirDockStory()));
        storyStep.getActions().add(new SaveGameAction("Save Game", "Save Game", this.engine));

        return storyStep;

    }


    public StoryStep getAirDockStory() {

        Location location = new Location()
            .setName("Near the air docks")
            .setDescription("You are near ship's dock.\n ");

        location.getEntities().add(new Entity("Creature", 40, Entity.State.AGGRESSIVE,
            new Item("Nails", "Itchy nails", 0.3d)));

        StoryStep storyStep = new BasicStoryStep(location);

        storyStep.getActions().add(new MoveAction("Into the dock", "Go into the docks", getInsideTheShip()));
        storyStep.getActions().add(new SaveGameAction("Save Game", "Save Game", this.engine));

        return storyStep;
    }

    private StoryStep getInsideTheShip(){
        Location location = new Location()
            .setName("Inside the ship")
            .setDescription(AsciiArtData.CORRIDOR +
                "You are inside the ship. It's rather dark in here and no sign of any life form .\n ");
        StoryStep storyStep = new BasicStoryStep(location);
        storyStep.getActions().add(new MoveAction("Move forward", "Move forward", getFinalStory()));
        storyStep.getActions().add(new MoveAction("Move backward", "Move backward", getBackwardStory()));
        storyStep.getActions().add(new SaveGameAction("Save Game", "Save Game", this.engine));

        return storyStep;
    }

    public StoryStep getBackwardStory() {

        Location location = new Location()
            .setName("Near the air docks")
            .setDescription(AsciiArtData.CORRIDOR +"You are near ship's dock.\n ");

        location.getEntities().add(new Entity("Something within the spacesuit", 10, Entity.State.AGGRESSIVE, new Item("Axe", "Axe", 0.2d)));

        StoryStep storyStep = new BasicStoryStep(location);


        storyStep.getActions().add(new SaveGameAction("Save Game", "Save Game", this.engine));
        storyStep.getActions().add(new MoveAction("Move farther", "Move farther", getFartherStory()));

        return storyStep;
    }

    public StoryStep getFartherStory() {

        Location location = new Location()
            .setName("Farther into the ship")
            .setDescription(AsciiArtData.CORRIDOR +"You are in the next ship section.\n ");

        location.getEntities().add(new Entity("Huge mechanical security bot", 30, Entity.State.AGGRESSIVE,
            new Item("Blaster", "Blaster", 0.3d)));

        StoryStep storyStep = new BasicStoryStep(location);

        storyStep.getActions().add(new MoveAction("Move farther", "Move farther", getFinal2Story()));

        return storyStep;
    }



    private StoryStep getFinalStory() {

        Location location = new Location()
            .setName("Final")
            .setDescription(AsciiArtData.CONTROL_PANEL +
                "You have reach ships control room, activate the power system and fly away heading nearest planet.\n " +
                "TO BE CONTINUED...");

        StoryStep storyStep = new BasicStoryStep(location);
        storyStep.getActions().add(new AbstractAction("Buy","Good bye!") {
            @Override
            public StoryStep doIt(StoryStep item, Character character) {
                engine.interrupt();
                return null;
            }
        });

        return storyStep;
    }

    private StoryStep getFinal2Story() {

        Location location = new Location()
            .setName("Final")
            .setDescription("You go farther and farther inside the ship. " +
                "You have got lost and see no hope for escape out of this place." +
                "\n TO BE CONTINUED...");

        StoryStep storyStep = new BasicStoryStep(location);
        storyStep.getActions().add(new AbstractAction("Buy","Good bye!") {
            @Override
            public StoryStep doIt(StoryStep item, Character character) {
                engine.interrupt();
                return null;
            }
        });

        return storyStep;
    }
}
