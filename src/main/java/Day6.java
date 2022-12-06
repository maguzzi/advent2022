import java.util.LinkedList;

public class Day6 extends Util implements Quiz {

    int sum = 0;

    private static final int BUFFER_SIZE_STEP_1 = 4;
    private static final int BUFFER_SIZE_STEP_2 = 14;

    Day6(Mode mode) {
        super(mode);
    }

    @Override
    public void doWorkOnLineStep1(String line) {
        scanBuffer(line, BUFFER_SIZE_STEP_1);
    }

    public void doWorkOnLineStep2(String line) {
        scanBuffer(line, BUFFER_SIZE_STEP_2);
    }

    private void scanBuffer(String line, int bufferSize) {
        LinkedList<String> buffer = new LinkedList<>();
        for (int i = 0; i < line.length(); i++) {
            buffer.push(line.charAt(i) + "");
            if (buffer.size() == bufferSize) {
                if (buffer.stream().distinct().count() == bufferSize) {
                    sum = i + 1;
                    break;
                }
                buffer.removeLast();
            }
        }
    }

    @Override
    public Result run() throws Exception {
        Result result = new Result();
        doWork(getFileName(), 1);
        result.setStep1(sum + "");
        sum = 0;
        doWork(getFileName(), 2);
        result.setStep2(sum + "");
        return result;
    }

}
