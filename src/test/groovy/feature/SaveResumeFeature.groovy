package feature

import local.playground.game.engine.Action
import local.playground.game.engine.Engine
import local.playground.game.util.SaveUtil
import spock.lang.Specification

/**
 *
 * As a player I want to save and resume game.
 */
class SaveResumeFeature extends Specification {

    def "save game state to a file"(){
        given:
        def engine = new Engine();
        if (new File(SaveUtil.SAVE_FILENAME).exists()){
            new File(SaveUtil.SAVE_FILENAME).delete()
        }

        when:
        engine.save();

        then:
        new File(SaveUtil.SAVE_FILENAME).exists()
    }


    def "load game state into memory"(){
        given:
            def engine = new Engine()
            def name = "A name for the test"
            engine.getCharacter().setName(name)
            def actionSize = engine.getCurrentStoryStep().getActions().size()
            def actionHash = getActionsHash(engine.getCurrentStoryStep().getActions());
            System.out.println("Action hash: " + actionHash)
            if (new File(SaveUtil.SAVE_FILENAME).exists()){
                new File(SaveUtil.SAVE_FILENAME).delete()
            }

        when:
            engine.save();
            def object = SaveUtil.load();

            then:
            object instanceof Engine
            Engine resurrectedEngine = (Engine)object
            resurrectedEngine.getCharacter().getName() == name
            resurrectedEngine.getCurrentStoryStep().getActions().size() == actionSize
            getActionsHash(resurrectedEngine.getCurrentStoryStep().getActions()) == actionHash
    }

    def getActionsHash(Collection<Action> action){
        def actionHash = ""
        action.each{act -> actionHash += act.getName()}
        actionHash
    }


}

