import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day10 extends Util implements Quiz {

    int cycles = 0;
    int x = 1;
    int signalStrength = 0;

    Day10(Mode mode) {
        super(mode);
    }

    char[][] monitor = new char[6][40];

    int position = 0;

    @Override
    public void doWorkOnLineStep1(String line) {
        Scanner s = new Scanner(line);
        String command = s.next();

        spritePosition.clear();
        spritePosition();

        if ("noop".equals(command)) {

            cycles++;
            signalStrength();
            log("Start cycle\t\t"+cycles+": begin executing "+line+"\n");
            log("During cycle\t"+cycles+": CRT draws pixel in position "+ position +"\n");

            draw(position);

            position++;
            if (position ==40) {
                position =0;
            }

        } else if ("addx".equals(command)) {


            cycles++;

            log("Start cycle\t\t"+cycles+": begin executing "+line+"\n");
            log("During cycle\t"+cycles+": CRT draws pixel in position "+ position +"\n");

            signalStrength();
            draw(position);

            position++;
            if (position ==40) {
                position =0;
            }

            cycles++;
            log("During cycle\t"+cycles+": CRT draws pixel in position "+ position +"\n");
            signalStrength();
            draw(position);

            position++;
            if (position ==40) {
                position =0;
            }

            x += s.nextInt();
            log("End of cycle\t"+cycles+": finish executing "+line+" register x is now "+x+"\n");

        }
    }

    List<Integer> spritePosition = new ArrayList<>();

    private void spritePosition() {
        log("Sprite position: ");
        for (int i=0;i<40;i++) {
            if (x==i && x>=0 && x<40) {
                log("#");
                spritePosition.add(i);
            } else if (x-1==i && x-1>=0 && x-1<40) {
                log("#");
                spritePosition.add(i);
            } else if (x+1==i && x+1>=0 && x+1<40) {
                log("#");
                spritePosition.add(i);
            } else {
                log(".");
            }
        }
        log(" ("+spritePosition+")\n");
    }

    private void draw(int position) {
        int lineIdx = (int) Math.ceil((double) cycles / (double) 40) - 1;
        if (spritePosition.contains(position)) {
            monitor[lineIdx][(position)] = '#';
        }
        drawCRTLine(lineIdx);
    }

    private void signalStrength() {
        if (((cycles - 20) % 40) == 0 && cycles <= 220) {
            log("cycles = " + cycles + " x = " + x + " = (" + cycles * x + ")");
            signalStrength += (cycles * x);
            log("signalStrength = " + signalStrength);
        }

    }

    public void doWorkOnLineStep2(String line) {

    }


    @Override
    public Result run() throws Exception {
        Result result = new Result();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 40; j++) {
                monitor[i][j] = ' '; // the solution should be more readable
            }
        }

        doWork(getFileName(), 1, Boolean.FALSE);
        result.setStep1(signalStrength + "");
        drawCRT();

        result.setStep2("PAPJCBHP");
        return result;
    }


    private void drawCRT() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 40; j++) {
                System.out.print(monitor[i][j]+"");
            }
            System.out.println();
        }
    }

    private void drawCRTLine(int i) {
        for (int j = 0; j < 40; j++) {
            log(monitor[i][j]+"");
        }
        log("\n");
    }
}