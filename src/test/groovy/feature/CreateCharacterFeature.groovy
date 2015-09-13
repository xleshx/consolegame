package feature

import local.playground.game.config.Config
import local.playground.game.engine.Engine
import local.playground.game.Console
import spock.lang.Specification

/**
 * As a Player I want to create character.
 */
class CreateCharacterFeature extends Specification{
    /**
     * Given: start of the game
     * When: player choose to start a new game
     * Then: Character initialized, default values are set
     */
    def "initialize character on start"(){
        when:
        def engine = new Engine()

        then:
        engine.getCharacter() != null
        engine.getCharacter().getHealth() == 100
        engine.getCharacter().getExperience() == 0
    }

    /**
     * Given: a New Game started
     * When: Player sets a name to Character
     * Then: the name is stored in the System
     */
    def "set characters name"(){
        given:
        def engine = new Engine()
        def itemFactory = new Config(engine);
        Console console = Mock();
        console.readActionInput() >> 1;
        console.readAdditionalData() >> "Jen"
        engine.setConsole(console);
        engine.setCurrentStoryStep(itemFactory.getCreateCharacterItem());

        when:

        engine.logic()

        then:
        engine.getCharacter().getName() == "Jen"
    }

}
