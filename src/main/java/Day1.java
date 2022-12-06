import java.util.LinkedList;

public class Day1 extends Util {

    LinkedList<Integer> tops = new LinkedList<Integer>();
    int max = 0;
    int max_elf = 1;

    int sum = 0;
    int elf = 1;

    int top_size=3;

    public static void main(String[] args) throws Exception {
        new Day1();
    }

    public Day1() throws Exception {
        doWork("./src/main/resources/day1.input.real",1);
        System.out.println("\nelf: " + max_elf + " max: " + max);
        System.out.println("sum top "+top_size+": "+ tops.stream().mapToInt(i -> i).sum());

    }

    private void changeMax() {
        max = sum;
        max_elf = elf;
        if (tops.size()==top_size) {
            tops.removeLast();
        }
        tops.push(max);
        System.out.println("elf: " + elf + " max: " + max);
        System.out.println("tops "+tops);

    }

    @Override
    public void doWorkOnLineStep1(String line) {
        if (line.equals("")) {
            if (sum > max) {
                changeMax();
            }
            sum = 0;
            elf++;
        } else {
            sum += Integer.parseInt(line);
            System.out.println("elf: " + elf + " sum: " + sum);
        }
    }

    @Override
    void doWorkOnLineStep2(String line) {

    }
}