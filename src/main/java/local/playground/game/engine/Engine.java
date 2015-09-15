package local.playground.game.engine;

import local.playground.game.ScannerConsole;
import local.playground.game.config.Config;
import local.playground.game.screen.Screen;
import local.playground.game.screen.StartScreen;
import local.playground.game.Console;
import local.playground.game.screen.LocationScreen;
import local.playground.game.util.SaveUtil;

import java.io.IOException;
import java.io.Serializable;
import java.lang.*;


/**
 * Engine class with the main game loop.
 */
public class Engine implements Serializable{
    private Config cfg;
    private transient Console console;
    private Character character;
    private StoryStep currentStoryStep;
    private boolean interrupted = false;


    public void save(){
        SaveUtil.save(this);
    }

    /**
     * TODO: catch exception and show an error to Player.
     * @return
     * @throws IOException
     */
    public StoryStep load() throws IOException {
        Object obj = SaveUtil.load();
        Engine loaded = (Engine) obj;
        this.cfg = loaded.cfg;
        this.character = loaded.getCharacter();
        this.currentStoryStep = loaded.getCurrentStoryStep();
        return this.currentStoryStep;
    }

    public Character getCharacter(){
        return character;
    }


    public Engine(){
        init();
    }

    public void init(){
        cfg = new Config(this);
        console = new ScannerConsole();
        Item beamGun = new Item("Beam gun", "Small beam gun", 0.7);
        character = new Character("", 100, Entity.State.NEUTRAL, beamGun);
        character.setExperience(0);
        currentStoryStep = cfg.getInitialStoryItem();
    }

    public void start(){
        Screen startScreen = new StartScreen(currentStoryStep);
        console.render(startScreen);
        Action action = readActionSafely();
        currentStoryStep = currentStoryStep.process(action, this.character);
        loop();
    }


    private void loop(){
        while(!this.interrupted){
            logic();
        }
        console.render(() -> new StringBuilder().append("Thank you for your time!"));
    }

    public void logic() {
        Screen screen = new LocationScreen(this.currentStoryStep);
        console.render(screen);
        this.currentStoryStep.getMessages().clear();
        Action action = readActionSafely();
        processAdditionalData(action);
        this.currentStoryStep = this.currentStoryStep.process(action, character);

    }

    private Action readActionSafely(){
        int actionId = console.readActionInput();
        Action action = this.currentStoryStep.getActionByOrderId(actionId);
        if (null == action){
            readActionSafely();
        }
        return action;
    }


    private void processAdditionalData(Action action) {
        if (action.isNeedAdditionalCode()){
            int codeId = console.readActionInput();
            action.setAdditionalCode(codeId);
        }

        if (action.isNeedAdditionalData()){
            String data = console.readAdditionalData();
            action.setAdditionalData(data);
        }
    }



    public void interrupt(){
        this.interrupted = true;
    }

    public StoryStep getCurrentStoryStep() {
        return currentStoryStep;
    }

    public void setCurrentStoryStep(StoryStep currentStoryStep) {
        this.currentStoryStep = currentStoryStep;
    }

    public void setConsole(Console console) {
        this.console = console;
    }
}
