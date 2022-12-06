import java.util.LinkedList;

public class Day6 extends Util {

    int charCount = 0;

    private static final int BUFFER_SIZE_STEP_1 = 4;
    private static final int BUFFER_SIZE_STEP_2 = 14;

    public static void main(String[] args) throws Exception {
        new Day6();
    }


    public Day6() throws Exception {
        charCount = 0;
        doWork("./src/main/resources/day6.input", 1);
        System.out.println("charCount: " + charCount);

        charCount = 0;
        doWork("./src/main/resources/day6.input.real", 2);
        System.out.println("charCount: " + charCount);

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
        System.out.println();
        for (int i = 0; i < line.length(); i++) {
            buffer.push(line.charAt(i) + "");
            if (buffer.size() == bufferSize) {
                if (buffer.stream().distinct().count() == bufferSize) {
                    charCount = i + 1;
                    System.out.println(charCount);
                    charCount = 0;
                    break;
                }
                buffer.removeLast();
            }
        }
    }

}
