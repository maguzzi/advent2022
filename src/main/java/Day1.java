import java.util.LinkedList;

public class Day1 extends Util implements Quiz {

    private static final int TOPS_SIZE_STEP_1 = 1;
    private static final int TOPS_SIZE_STEP_2 = 3;

    LinkedList<Integer> tops = new LinkedList<Integer>();
    int max = 0;
    int max_elf = 1;

    int sum = 0;
    int elf = 1;

    Day1(Mode mode) {
        super(mode);
    }

    private void changeMax(int step) {
        max = sum;
        max_elf = elf;
        if (tops.size() == (step == 1 ? TOPS_SIZE_STEP_1 : TOPS_SIZE_STEP_2)) {
            tops.removeLast();
        }
        tops.push(max);
        if (Mode.EXAMPLE.equals(mode)) {
            System.out.println(tops);
        }

    }

    @Override
    public void doWorkOnLineStep1(String line) {
        innerRun(line, 1);
    }

    @Override
    void doWorkOnLineStep2(String line) {
        innerRun(line, 2);
    }

    private void innerRun(String line, int step) {
        if (line.equals("")) {
            if (sum > max) {
                changeMax(step);
            }
            sum = 0;
            elf++;
        } else {
            sum += Integer.parseInt(line);
        }
    }

    @Override
    public Result run() throws Exception {
        Result result = new Result();
        max = 0;
        max_elf = 1;
        sum = 0;
        elf = 1;
        tops.clear();
        doWork(getFileName(), 1);
        result.setStep1((Integer) tops.stream().mapToInt(it -> it).sum() +"");
        max = 0;
        max_elf = 1;
        sum = 0;
        elf = 1;
        tops.clear();
        doWork(getFileName(), 2);
        result.setStep2((Integer) tops.stream().mapToInt(it -> it).sum() +"");
        return result;
    }
}
