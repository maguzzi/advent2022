import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day9 extends Util implements Quiz {

    Day9(Mode mode) {
        super(mode);
    }

    Direction previousDirection;

    int positionVisited=0;

    @Override
    public void doWorkOnLineStep1(String line) {
        Scanner s = new Scanner(line);
        Direction direction = Direction.valueOf(s.next());
        int num = s.nextInt();
        log("move " + direction.desc() + " " + num + " blocks\n");
        for (int i = 0; i < num; i++) {
            switch (direction) {
                case R:
                    switch (previousDirection) {
                        case R:
                            break;
                        case U:
                            break;
                        case L:
                            break;
                        case D:
                            break;
                        default:
                            break;
                    }
                    break;
                case U:
                    switch (previousDirection) {
                        case R:
                            break;
                        case U:
                            break;
                        case L:
                            break;
                        case D:
                            break;
                        default:
                            break;
                    }
                    break;
                case L:
                    switch (previousDirection) {
                        case R:
                            break;
                        case U:
                            break;
                        case L:
                            break;
                        case D:
                            break;
                        default:
                            break;
                    }
                    break;
                case D:
                    switch (previousDirection) {
                        case R:
                            break;
                        case U:
                            break;
                        case L:
                            break;
                        case D:
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
            previousDirection = direction;
        }

    }

    public void doWorkOnLineStep2(String line) {

    }



    @Override
    public Result run() throws Exception {
        Result result = new Result();
        doWork(getFileName(), 1, Boolean.FALSE);
        result.setStep1(positionVisited+"");
        return result;
    }

}

enum Direction {


    U("UP"),
    D("DOWN"),
    L("LEFT"),
    R("RIGHT");

    private String desc;

    Direction(String desc) {
        this.desc = desc;
    }

    public String desc() {
        return desc;
    }
}
