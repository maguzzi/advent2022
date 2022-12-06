import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 extends Util implements Quiz {

    int sum = 0;
    private List<String> list = new ArrayList<>();

    Day4(Mode mode) {
        super(mode);
    }

    @Override
    public void doWorkOnLineStep1(String line) {
        Day4ParsedLine l = parseLine(line);
        if ((l.a >= l.c && l.b <= l.d) || (l.c >= l.a && l.d <= l.b)) {
            sum++;
        }
    }

    public void doWorkOnLineStep2(String line) {
        Day4ParsedLine l = parseLine(line);
        if ((l.b >= l.c && l.c >= l.a) || (l.a >= l.c && l.d >= l.a)) {
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

    @Override
    public Result run() throws Exception {
        Result result = new Result();
        doWork(getFileName(), 1);
        result.setStep1(sum+"");
        sum = 0;
        doWork(getFileName(), 2);
        result.setStep2(sum+"");
        return result;
    }
}

class Day4ParsedLine {
    int a;
    int b;
    int c;
    int d;
}
