import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Util {
    public void doWork(String inputPath) throws Exception {

        LineInputStream lis = new LineInputStream(Files.newInputStream(Paths.get(inputPath)));

        try {

            String line = lis.readLine();
            while (line != null) {
                doWorkOnLine(line);
                line = lis.readLine();
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (lis!=null) {
                lis.close();
            }
        }

    }
    public abstract void doWorkOnLine(String line);
}
