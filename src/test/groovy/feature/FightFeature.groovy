package feature

import local.playground.game.Console
import local.playground.game.config.Config
import local.playground.game.engine.Action
import local.playground.game.engine.step.BasicStoryStep
import local.playground.game.engine.step.CombatStep
import local.playground.game.engine.Engine
import local.playground.game.engine.Entity
import local.playground.game.engine.Item
import local.playground.game.engine.Location
import local.playground.game.engine.action.fight.AttackWithAction
import spock.lang.Specification


/**
 * As a Player I want to gain experience through fighting
 */
class FightFeature extends Specification {
    /**
     * Given normal Game state
     * When Player get to the Location with the opponent Entity alive
     * Then actions for attack with available items should appear
     */

    def "player go into a fight"(){
        given:
          Engine engine = new Engine();
          def cfg = new Config(engine);
          engine.setCurrentStoryStep(cfg.getAirDockStory());
          def console = Mock(Console);
          console.readActionInput() >> 1;
          engine.setConsole(console);

          when:
            engine.logic();

          then:
          Collection<Action> actions = engine.getCurrentStoryStep().getActions();
          actions.size() > 0
          actions.stream().filter({action -> action instanceof AttackWithAction}).count() > 0
    }

    /**
     * Given Player ready to fight
     * When Player attacks opponent with the Item
     * Then fight should be proceeded
     * And Player experience should be increased
     */
    def "player attacks with the item"(){
        given:
            def location = Mock(Location);
            def item = new Item("TestItem", "Test Item description", 0.2d);
            def entity = new Entity("Test Entity", 20, Entity.State.AGGRESSIVE, item);
            location.getEntities() >> Arrays.asList(entity);

            Engine engine = new Engine();
            engine.setCurrentStoryStep(new CombatStep(location, engine.getCharacter(), entity, new BasicStoryStep(new Location())));

            def console = Mock(Console);
            console.readActionInput() >> 1;
            engine.setConsole(console);
        when:
            engine.logic()
        then:
            entity.getState() == Entity.State.DEAD
            !engine.getCurrentStoryStep().getMessages().isEmpty()
            engine.getCharacter().getExperience() > 0
    }

}
