import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.stream;

public class Day5 extends Util implements Quiz {

    private static final int CONTAINER_NUMBER_EXAMPLE = 3;
    private static final int CONTAINER_NUMBER_REAL = 9;

    static private LinkedList<String>[] containers;

    static void init(Mode mode) {

        containers = new LinkedList[(Mode.EXAMPLE.equals(mode)?CONTAINER_NUMBER_EXAMPLE:CONTAINER_NUMBER_REAL)];

        for (int i = 0; i < (Mode.EXAMPLE.equals(mode)?CONTAINER_NUMBER_EXAMPLE:CONTAINER_NUMBER_REAL); i++) {
            containers[i] = new LinkedList<>();
        }

        if (Mode.EXAMPLE.equals(mode)) {

            stream(new String[]{"N", "Z"}).forEach(it -> containers[0].push(it));
            stream(new String[]{"D", "C", "M"}).forEach(it -> containers[1].push(it));
            stream(new String[]{"P"}).forEach(it -> containers[2].push(it));

        } else if (Mode.REAL.equals(mode)) {

            stream(new String[]{"L", "C", "G", "M", "Q"}).forEach(it -> containers[0].push(it));
            stream(new String[]{"G", "H", "F", "T", "C", "L", "D", "R"}).forEach(it -> containers[1].push(it));
            stream(new String[]{"R", "W", "T", "M", "N", "F", "J", "V"}).forEach(it -> containers[2].push(it));
            stream(new String[]{"P", "Q", "V", "D", "F", "J"}).forEach(it -> containers[3].push(it));
            stream(new String[]{"T", "B", "L", "S", "M", "F", "N"}).forEach(it -> containers[4].push(it));
            stream(new String[]{"P", "D", "C", "H", "V", "N", "R"}).forEach(it -> containers[5].push(it));
            stream(new String[]{"T", "C", "H"}).forEach(it -> containers[6].push(it));
            stream(new String[]{"P", "H", "N", "Z", "V", "J", "S", "G"}).forEach(it -> containers[7].push(it));
            stream(new String[]{"G", "H", "F", "Z"}).forEach(it -> containers[8].push(it));
        }

    }

    Day5(Mode mode) {
        super(mode);
    }

    private String getAns() {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < (Mode.EXAMPLE.equals(mode)?CONTAINER_NUMBER_EXAMPLE:CONTAINER_NUMBER_REAL); i++) {
            ans.append(containers[i].getLast());
        }
        return ans.toString();
    }

    private void render() {
        if (Mode.EXAMPLE.equals(mode)) {
            System.out.println("\n************");
            for (int i = 0; i < (Mode.EXAMPLE.equals(mode) ? CONTAINER_NUMBER_EXAMPLE : CONTAINER_NUMBER_REAL); i++) {
                System.out.println((i + 1) + " " + containers[i]);
            }
            System.out.println("************");
        }

    }

    private ParsedLine parseLine(String line) {
        Scanner s = new Scanner(line);
        s.next();
        int times = s.nextInt();
        s.next();
        int fromIdx = s.nextInt() - 1;
        s.next();
        int toIdx = s.nextInt() - 1;
        return new ParsedLine(fromIdx, toIdx, times);
    }

    @Override
    public void doWorkOnLineStep1(String line) {
        ParsedLine parsedLine = parseLine(line);
        for (int i = 0; i < parsedLine.getTimes(); i++) {
            String tmp = containers[parsedLine.getFromIdx()].removeLast();
            containers[parsedLine.getToIdx()].addLast(tmp);
        }
        render();
    }

    @Override
    public void doWorkOnLineStep2(String line) {

        ParsedLine parsedLine = parseLine(line);

        List<String> buffer = new ArrayList<>();
        for (int i = 0; i < parsedLine.getTimes(); i++) {
            buffer.add(0, containers[parsedLine.getFromIdx()].removeLast());
        }
        for (int i = 0; i < parsedLine.getTimes(); i++) {
            containers[parsedLine.getToIdx()].addLast(buffer.get(i));
        }

        render();
    }

    @Override
    public Result run() throws Exception {
        Result result = new Result();
        init(mode);
        render();
        doWork(getFileName(), 1);
        result.setStep1(getAns());
        init(mode);
        doWork(getFileName(), 2);
        result.setStep2(getAns());
        return result;
    }
}

class ParsedLine {
    int fromIdx;
    int toIdx;
    int times;

    public ParsedLine(int fromIdx, int toIdx, int times) {
        this.fromIdx = fromIdx;
        this.toIdx = toIdx;
        this.times = times;
    }

    public int getFromIdx() {
        return fromIdx;
    }


    public int getToIdx() {
        return toIdx;
    }


    public int getTimes() {
        return times;
    }

}
