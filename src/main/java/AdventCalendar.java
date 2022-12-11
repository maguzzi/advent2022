import java.util.ArrayList;
import java.util.List;

public class AdventCalendar {
    public static void main(String args[]) {
        List<Quiz> quizzes = new ArrayList<>();

        /*
        quizzes.add(new Day1(Mode.REAL));
        quizzes.add(new Day2(Mode.REAL));
        quizzes.add(new Day3(Mode.REAL));
        quizzes.add(new Day4(Mode.REAL));
        quizzes.add(new Day5(Mode.REAL));
        quizzes.add(new Day6(Mode.REAL));
        quizzes.add(new Day7(Mode.REAL));
        quizzes.add(new Day8(Mode.REAL));
        */
        quizzes.add(new Day9(Mode.EXAMPLE));

        /*
        quizzes.add(new Day10(Mode.REAL));
         */


        quizzes.forEach(q -> {
            System.out.println("*** " + q.getClass().getSimpleName() + " ***");
            try {
                Result r = q.run();
                System.out.println("Result");
                System.out.println("step 1: " + r.getStep1());
                System.out.println("step 2: " + r.getStep2());
            } catch (Exception e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
            System.out.println("************");
            System.out.println();

        });
    }
}
