import java.util.ArrayList;
import java.util.List;

public class Day12 extends Util implements Quiz {

    List<List<String>> map = new ArrayList<>();

    int start_i;
    int start_j;
    int end_i;
    int end_j;

    Day12(Mode mode) {
        super(mode);
    }

    @Override
    public void doWorkOnLineStep1(String line) {
        List<String> l = new ArrayList<>();
        List<String> lD = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            lD.add(".");
            if (line.charAt(i) == 'S') {
                start_i = lineIdx;
                start_j = i;
                l.add("a");
            } else if (line.charAt(i) == 'E') {
                end_i = lineIdx;
                end_j = i;
                l.add("z");
            } else {
                l.add(line.charAt(i) + "");
            }
        }
        mapD.add(lD);
        map.add(l);
    }


    public void doWorkOnLineStep2(String line) {

    }


    Point start;

    int moves=0;

    @Override
    public Result run() throws Exception {
        Result result = new Result();

        doWork(getFileName(), 1, Boolean.FALSE);

        printMap(map);
        System.out.println("start_i " + start_i);
        System.out.println("start_j " + start_j);
        System.out.println("end_i " + end_i);
        System.out.println("end_j " + end_j);


        start = new Point(start_i, start_j);
        // while(start_i!=end_i || start_j!=end_j) {
        step(start);

        // }

        result.setStep1(moves+"");
        return result;
    }

    List<List<String>> mapD = new ArrayList<>();

    private Point move() {
        System.out.println("moved to "+start_i+" "+start_j);
        Point newPoint = new Point(start_i, start_j);
        newPoint.parent = start;
        start.possibleDirections.add(newPoint);
        start = newPoint;
        return start;
    }


    void step(Point start) {
        printMap(mapD);

        if (start.i == end_i && start.j == end_j) {
            System.out.println("***************");
            return;
        }

        moves++;

        if (start_i - 1 >= 0 && notVisited(start, start_i - 1, start_j) && (map.get(start_i - 1).get(start_j).charAt(0) == map.get(start_i).get(start_j).charAt(0) + 1)) {
            mapD.get(start_i).remove(start_j);
            mapD.get(start_i).add(start_j, "^");
            start_i--;
            step(move());
        }
        if (start_j - 1 >= 0 && notVisited(start, start_i, start_j - 1) && (map.get(start_i).get(start_j - 1).charAt(0) == map.get(start_i).get(start_j).charAt(0) + 1)) {
            mapD.get(start_i).remove(start_j);
            mapD.get(start_i).add(start_j, "<");
            start_j--;
            step(move());
        }
        if (start_j + 1 < inputWidth && notVisited(start, start_i, start_j + 1) && (map.get(start_i).get(start_j + 1).charAt(0) == map.get(start_i).get(start_j).charAt(0) + 1)) {
            mapD.get(start_i).remove(start_j);
            mapD.get(start_i).add(start_j, ">");
            start_j++;
            step(move());
        }
        if (start_i + 1 < inputHeight && notVisited(start, start_i + 1, start_j) && (map.get(start_i + 1).get(start_j).charAt(0) == map.get(start_i).get(start_j).charAt(0) + 1)) {
            mapD.get(start_i).remove(start_j);
            mapD.get(start_i).add(start_j, "v");
            start_i++;
            step(move());
        }
        if (start_i - 1 >= 0 && notVisited(start, start_i - 1, start_j) && map.get(start_i - 1).get(start_j).charAt(0) == map.get(start_i).get(start_j).charAt(0)) {
            mapD.get(start_i).remove(start_j);
            mapD.get(start_i).add(start_j, "^");
            start_i--;
            step(move());
        }
        if (start_j - 1 >= 0 && notVisited(start, start_i, start_j - 1) && map.get(start_i).get(start_j - 1).charAt(0) == map.get(start_i).get(start_j).charAt(0)) {
            mapD.get(start_i).remove(start_j);
            mapD.get(start_i).add(start_j, "<");
            start_j--;
            step(move());
        }
        if (start_j + 1 < inputWidth && notVisited(start, start_i, start_j + 1) && map.get(start_i).get(start_j + 1).charAt(0) == map.get(start_i).get(start_j).charAt(0)) {
            mapD.get(start_i).remove(start_j);
            mapD.get(start_i).add(start_j, ">");
            start_j++;
            step(move());
        }
        if (start_i + 1 < inputHeight && notVisited(start, start_i + 1, start_j) && map.get(start_i + 1).get(start_j).charAt(0) == map.get(start_i).get(start_j).charAt(0)) {
            mapD.get(start_i).remove(start_j);
            mapD.get(start_i).add(start_j, "v");
            start_i++;
            step(move());
        }

    }

    boolean notVisited(Point p, int i, int j) {
        if (p.i==i && p.j==j) {
            return false;
        }
        while (p.parent != null) {
            if (p.parent.i == i && p.parent.j == j) {
                return false;
            }
            p = p.parent;
        }
        return true;
    }

    void printMap(List<List<String>> map) {
        for (int i = 0; i < map.size(); i++) {
            List<String> line = map.get(i);
            for (int j = 0; j < line.size(); j++) {
                System.out.print(line.get(j));
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Point {
    Point(int i, int j) {
        this.i = i;
        this.j = j;
    }

    int i;
    int j;

    Point parent;

    List<Point> possibleDirections = new ArrayList<>();
}