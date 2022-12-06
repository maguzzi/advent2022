import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Day3 extends Util implements Quiz {

    int sum = 0;
    private List<String> list = new ArrayList<>();

    Day3(Mode mode) {
        super(mode);
    }

    @Override
    public void doWorkOnLineStep1(String line) {
        String firstHalf = line.substring(0, line.length() / 2);
        String secondHalf = line.substring(line.length() / 2, line.length());
        assert (line.equals(firstHalf + secondHalf));
        List<Character> x = charInCommon(firstHalf, secondHalf);
        if (!x.isEmpty()) {
            int p = getPriority(x.get(0));
            sum += p;
        }
    }

    @Override
    public void doWorkOnLineStep2(String line) {
        list.add(line);
        if (list.size() == 3) {
            List<Character> x = charInCommon(list.get(0), list.get(1));
            List<Character> y = charInCommon(list.get(1), list.get(2));
            List<Character> z = charInCommon(list.get(0), list.get(2));
            Optional<Character> collect = x.stream().filter(y::contains).filter(z::contains).findFirst();
            sum += getPriority(collect.get());
            list.clear();
        }
    }

    private List<Character> charInCommon(String a, String b) {
        List<Character> commonChars = new ArrayList<>();
        for (int i = 0; i < a.length(); i++) {
            char x = a.charAt(i);
            if (b.contains(x + "")) {
                commonChars.add(x);
            }
        }
        return commonChars.stream().distinct().collect(Collectors.toList());
    }

    private int getPriority(char x) {
        if (x >= 'a' && x <= 'z') {
            return x - 'a' + 1;
        } else {
            return x - 'A' + 27;
        }
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
