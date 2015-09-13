package feature

import local.playground.game.Console
import local.playground.game.config.Config
import local.playground.game.engine.Engine
import spock.lang.Specification


/**
 * As a Player I want to explore
 */
class ExploreFeature extends Specification {

    /**
     * Given: Character is in the certain Location
     * When: Player do a move
     * Then: Character move to a new Location
     */
    def "move between locations"(){
        given:
        Engine engine = new Engine();
        Config factory = new Config(engine);
        factory.getBaseStoryItem();
        engine.setCurrentStoryStep(factory.getBaseStoryItem());
        Console console = Mock();
            console.readActionInput() >> 1;
        engine.setConsole(console);
        def initialLocationDescr = engine.getCurrentStoryStep().getLocation().getDescription();

        when:
        engine.logic();

        then:
        engine.getCurrentStoryStep().getLocation().getDescription() != initialLocationDescr
    }



}
