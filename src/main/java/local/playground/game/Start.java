package local.playground.game;

import local.playground.game.engine.Engine;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Main start class.
 *
 */
public class Start {
    private Logger log = Logger.getLogger(Start.class.getName());
    public static void main(String[] args){
        try {
            LogManager.getLogManager().readConfiguration(Start.class.getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            System.out.println("Unable to initialize logger");
        }
        Engine engine = new Engine();
        engine.start();
    }

}
