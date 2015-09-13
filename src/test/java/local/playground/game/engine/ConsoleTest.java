package local.playground.game.engine;

import local.playground.game.ScannerConsole;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;

/**
 * Created by lesh on 12.09.15.
 */
public class ConsoleTest {

    private ScannerConsole console;
    private PrintStream ps;
    private InputStream is;
    private ByteArrayOutputStream baos;

    @Before
    public void init(){
        console = new ScannerConsole();
        baos = new ByteArrayOutputStream();
        ps = new PrintStream(baos);
        is = new ByteArrayInputStream(new byte[]{1});


        try {
            Field fOut = console.getClass().getDeclaredField("out");
            fOut.setAccessible(true);
            fOut.set(console, ps);

            Field fIn = console.getClass().getDeclaredField("in");
            fIn.setAccessible(true);
            fIn.set(console, is);



        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadAdditionalCode(){
        System.out.print(baos.toString());
        //console.readAdditionalCode();


    }



}
