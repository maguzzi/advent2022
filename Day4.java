import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

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
    public void doWorkOnLine(String line, int step) {
        Scanner s = new Scanner(line);
        s.useDelimiter("[,-]");
        int a = s.nextInt();
        int b = s.nextInt();
        int c = s.nextInt();
        int d = s.nextInt();
        if (step == 1) {
            if ((a>=c && b<=d) || (c>=a&&d<=b)){
                System.out.print("*");
                sum++;
            }
        } else if (step == 2) {
            if ((b>=c && c>=a) || (a>=c && d>=a)){
                System.out.print("*");
                sum++;
            }
        }
    }


}
