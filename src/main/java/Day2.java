import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day2 extends Util {
    public static void main(String[] args) throws Exception {
        new Day2();
    }

    static Map<String, Integer> movesOne = new HashMap<>();
    static Map<String, Integer> movesTwo = new HashMap<>();

    static {
        // PART 1
        movesOne.put("AX", 1 + 3);     // rock rock - draw
        movesOne.put("AY", 2 + 6);     // rock paper - won
        movesOne.put("AZ", 3 + 0);     // rock scissors - lost
        movesOne.put("BX", 1 + 0);     // paper rock - lost
        movesOne.put("BY", 2 + 3);     // paper paper - draw
        movesOne.put("BZ", 3 + 6);     // paper scissors - won
        movesOne.put("CX", 1 + 6);     // scissor rock - won
        movesOne.put("CY", 2 + 0);     // scissor paper - lost
        movesOne.put("CZ", 3 + 3);     // scissors scissors - draw

        // PART 2
        movesTwo.put("AX", 3 + 0);     // rock lose - scissor
        movesTwo.put("AY", 1 + 3);     // rock draw - rock
        movesTwo.put("AZ", 2 + 6);     // rock win - paper
        movesTwo.put("BX", 1 + 0);     // paper lose - rock
        movesTwo.put("BY", 2 + 3);     // paper draw - paper
        movesTwo.put("BZ", 3 + 6);     // paper win - scissor
        movesTwo.put("CX", 2 + 0);     // scissor lose - paper
        movesTwo.put("CY", 3 + 3);     // scissor draw - scissor
        movesTwo.put("CZ", 1 + 6);     // scissors win - rock

    }

    int sum = 0;

    public Day2() throws Exception {
        doWork("./src/main/resources/day2.input.real", 1);
        System.out.println(sum);
        sum = 0;
        doWork("./src/main/resources/day2.input.real", 2);
        System.out.println(sum);

    }


    @Override
    public void doWorkOnLineStep1(String line) {
        Scanner s = new Scanner(line);
        String elf = s.next("\\w");
        String me = s.next("\\w");
        sum += movesOne.get(elf + me);

    }

    public void doWorkOnLineStep2(String line) {
        Scanner s = new Scanner(line);
        String elf = s.next("\\w");
        String me = s.next("\\w");
        sum += movesTwo.get(elf + me);

    }
}
