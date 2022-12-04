import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public abstract class Util {
    public void doWork(String inputPath,int step) throws Exception {
        long t0 = new Date().getTime();
        LineInputStream lis = new LineInputStream(Files.newInputStream(Paths.get(inputPath)));

        try {

            String line = lis.readLine();
            while (line != null) {
                doWorkOnLine(line,step);
                line = lis.readLine();
                System.out.println();
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (lis!=null) {
                lis.close();
            }
        }
        long t1 = new Date().getTime();
        System.out.println("elapsed: "+new SimpleDateFormat("mm:ss.SSS").format(new Date(t1-t0)));

    }
    abstract void doWorkOnLine(String line, int step);
}
