package local.playground.game;

import local.playground.game.screen.Screen;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Console implementation based on Scanner.
 */
public class ScannerConsole implements Console{

    private String INVITATION_INT = "Please enter a number: ";
    private String INVITATION_SRING = "Please enter: ";

    private PrintStream out = System.out;
    private InputStream in = System.in;

    private Scanner scanner = new Scanner(this.in);

    @Override
    public void render(Screen screen) {
        this.out.print(screen.getView());
    }

    public int readActionInput(){
        return scanner.nextInt();
    }

    public int readAdditionalCode(){
        print(INVITATION_INT);
        return scanner.nextInt();
    }

    public String readAdditionalData(){
        print(INVITATION_SRING);
        return scanner.next();
    }

    private void print(String s){
        this.out.print(s);
    }

}
