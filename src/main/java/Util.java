import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

abstract class Util {

    Mode mode;

    Util(Mode mode) {
        this.mode = mode;
    }

    void doWork(String inputPath, int step) throws Exception {
        long t0 = new Date().getTime();
        LineInputStream lis = new LineInputStream(Files.newInputStream(Paths.get(inputPath)));

        try {

            String line = lis.readLine();
            while (line != null) {
                if (Mode.EXAMPLE.equals(mode)) {
                    System.out.print(line + " ");
                }
                if (step == 1) {
                    doWorkOnLineStep1(line);
                } else if (step == 2) {
                    doWorkOnLineStep2(line);
                }

                line = lis.readLine();
                if (Mode.EXAMPLE.equals(mode)) {
                    System.out.println();
                }
            }

        } catch (Exception e) {
            throw e;
        } finally {
            lis.close();
            long t1 = new Date().getTime();
            System.out.println("mode: " + mode + " step: " + step + " elapsed: " + new SimpleDateFormat("mm:ss.SSS").format(new Date(t1 - t0)));
        }
    }

    abstract void doWorkOnLineStep1(String line);

    abstract void doWorkOnLineStep2(String line);

    String getFileName() {
        return "src/main/resources/" + getClass().getSimpleName().toLowerCase() + ".input." + mode.name().toLowerCase();
    }
}
