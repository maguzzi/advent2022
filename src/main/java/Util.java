import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

abstract class Util {

    Mode mode;

    Util(Mode mode) {
        this.mode = mode;
    }

    void doWork(String inputPath, int step) throws Exception {
        doWork(inputPath,step,Boolean.TRUE);
    }

    void doWork(String inputPath, int step,Boolean printInput) throws Exception {
        long t0 = new Date().getTime();
        List<String> lines = Files.readAllLines(Paths.get(inputPath));

        try {
            for (String line : lines) {
                if (Mode.EXAMPLE.equals(mode) && printInput) {
                    System.out.print(line + " ");
                }
                if (step == 1) {
                    doWorkOnLineStep1(line);
                } else if (step == 2) {
                    doWorkOnLineStep2(line);
                }

                if (Mode.EXAMPLE.equals(mode) && printInput) {
                    System.out.println();
                }
            }

        } catch (Exception e) {
            throw e;
        } finally {
            long t1 = new Date().getTime();
            System.out.println("mode: " + mode + " step: " + step + " elapsed: " + new SimpleDateFormat("mm:ss.SSS").format(new Date(t1 - t0)));
        }
    }

    abstract void doWorkOnLineStep1(String line);

    abstract void doWorkOnLineStep2(String line);

    String getFileName() {
        return "src/main/resources/" + getClass().getSimpleName().toLowerCase() + ".input." + mode.name().toLowerCase();
    }

    void log(String log) {
        if (mode.equals(Mode.EXAMPLE)) {
            System.out.print(log);
        }
    }
}
