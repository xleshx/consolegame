package local.playground.game.util;

import java.io.*;

/**
 * Load Save utils.
 */
public class SaveUtil {

    public static String SAVE_FILENAME = "game.save";

    public static void save(Serializable obj) {
        ObjectOutput out = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(SAVE_FILENAME, false);
            out = new ObjectOutputStream(fos);
            out.writeObject(obj);
        } catch (IOException e) {
            // quick and dirty
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
            }
            try {
                fos.flush();
                fos.close();
            } catch (IOException ex) {
            }
        }

    }

    public static Object load() throws IOException {
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(SAVE_FILENAME);
            InputStream bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ois.close();
        }
        // should be done in more correct way
        return null;
    }
}
