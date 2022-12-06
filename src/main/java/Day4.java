import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 extends Util {

    int sum = 0;
    private List<String> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        new Day4();
    }


    public Day4() throws Exception {
        doWork("./src/main/resources/day4.input.real", 1);
        System.out.println("sum " + sum);
        sum = 0;
        doWork("./src/main/resources/day4.input.real", 2);
        System.out.println("sum " + sum);
    }


    @Override
    public void doWorkOnLineStep1(String line) {
        Day4ParsedLine l = parseLine(line);
        if ((l.a >= l.c && l.b <= l.d) || (l.c >= l.a && l.d <= l.b)) {
            System.out.print("*");
            sum++;
        }
    }

    public void doWorkOnLineStep2(String line) {
        Day4ParsedLine l = parseLine(line);
        if ((l.b >= l.c && l.c >= l.a) || (l.a >= l.c && l.d >= l.a)) {
            System.out.print("*");
            sum++;
        }
    }

    private Day4ParsedLine parseLine(String line) {
        Scanner s = new Scanner(line);
        s.useDelimiter("[,-]");
        Day4ParsedLine day4ParsedLine = new Day4ParsedLine();
        day4ParsedLine.a = s.nextInt();
        day4ParsedLine.b = s.nextInt();
        day4ParsedLine.c = s.nextInt();
        day4ParsedLine.d = s.nextInt();
        return day4ParsedLine;
    }

}

class Day4ParsedLine {
    int a;
    int b;
    int c;
    int d;
}
