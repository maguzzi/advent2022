import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Day5 extends Util {

    private static final int CONTAINER_NUMBER = 9;

    private static final String mode = "REAL";

    static private final LinkedList<String>[] containers = new LinkedList[CONTAINER_NUMBER];

    static void init() {
        for (int i = 0; i < CONTAINER_NUMBER; i++) {
            containers[i] = new LinkedList<>();
        }

        if ("EXAMPLE".equals(mode)) {
            containers[0].push("N");
            containers[0].push("Z");

            containers[1].push("D");
            containers[1].push("C");
            containers[1].push("M");

            containers[2].push("P");
        } else if ("REAL".equals(mode)) {

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


    public static void main(String[] args) throws Exception {
        new Day5();
    }


    public Day5() throws Exception {

        init();

        render();

        doWork("./src/main/resources/day5.input.real", 1);

        printAns();

        init();

        doWork("./src/main/resources/day5.input.real", 2);

        printAns();

    }

    private void printAns() {
        String ans = "";
        for (int i = 0; i < CONTAINER_NUMBER; i++) {
            ans += containers[i].getLast();
        }
        System.out.println(ans);

    }

    private void render() {
        System.out.println("\n************");
        for (int i = 0; i < CONTAINER_NUMBER; i++) {
            System.out.println((i + 1) + " " + containers[i]);
        }
        System.out.println("************");

    }

    @Override
    public void doWorkOnLine(String line, int step) {
        System.out.print(line + " ");
        Scanner s = new Scanner(line);
        s.next();
        int times = s.nextInt();
        s.next();
        int fromIdx = s.nextInt() - 1;
        s.next();
        int toIdx = s.nextInt() - 1;
        if (step == 1) {
            for (int i = 0; i < times; i++) {
                String tmp = containers[fromIdx].removeLast();
                containers[toIdx].addLast(tmp);
            }
        } else if (step == 2) {
            List<String> buffer = new ArrayList<>();
            for (int i = 0; i < times; i++) {
                buffer.add(0,containers[fromIdx].removeLast());
            }
            for (int i = 0; i < times; i++) {
                containers[toIdx].addLast(buffer.get(i));
            }
        }

        render();
    }


}
