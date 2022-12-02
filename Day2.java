import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws IOException {
        new Day2();
    }

    Map<String, Integer> moves = new HashMap<>();

    public Day2() throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        LineInputStream lis = new LineInputStream(Files.newInputStream(Paths.get("./src/main/resources/day2.input.real")));

        // PART 1
        //        moves.put("AX", 1 + 3);     // rock rock - draw
        //        moves.put("AY", 2 + 6);     // rock paper - won
        //        moves.put("AZ", 3 + 0);     // rock scissors - lost
        //        moves.put("BX", 1 + 0);     // paper rock - lost
        //        moves.put("BY", 2 + 3);     // paper paper - draw
        //        moves.put("BZ", 3 + 6);     // paper scissors - won
        //        moves.put("CX", 1 + 6);     // scissor rock - won
        //        moves.put("CY", 2 + 0);     // scissor paper - lost
        //        moves.put("CZ", 3 + 3);     // scissors scissors - draw

        // PART 2
        moves.put("AX", 3 + 0);     // rock lose - scissor
        moves.put("AY", 1 + 3);     // rock draw - rock
        moves.put("AZ", 2 + 6);     // rock win - paper
        moves.put("BX", 1 + 0);     // paper lose - rock
        moves.put("BY", 2 + 3);     // paper draw - paper
        moves.put("BZ", 3 + 6);     // paper win - scissor
        moves.put("CX", 2 + 0);     // scissor lose - paper
        moves.put("CY", 3 + 3);     // scissor draw - scissor
        moves.put("CZ", 1 + 6);     // scissors win - rock

        int sum = 0;

        String line = lis.readLine();
        while (line != null) {
            Scanner s = new Scanner(line);
            String elf = s.next("\\w");
            String me = s.next("\\w");
            System.out.println("elf :" + elf + " me: " + me);
            sum += moves.get(elf + me);
            line = lis.readLine();
        }

        System.out.println(sum);

    }


}
