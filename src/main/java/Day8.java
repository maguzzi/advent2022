import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day8 extends Util implements Quiz {

    private List<List<Integer>> quadcopterMapByRows = new ArrayList<>();
    private List<List<Integer>> quadcopterMapByCols = new ArrayList<>();

    int rows = 0;
    int cols = 0;

    Day8(Mode mode) {
        super(mode);
    }

    @Override
    public void doWorkOnLineStep1(String line) {
        List<Integer> row = new ArrayList<>();
        cols = 0;
        for (int i = 0; i < line.length(); i++) {
            row.add(Integer.parseInt(line.charAt(i) + ""));
            cols++;
        }
        quadcopterMapByRows.add(row);
        rows++;
    }

    public void doWorkOnLineStep2(String line) {

    }

    int maxScenicScore = 0;

    @Override
    public Result run() throws Exception {
        Result result = new Result();
        int visibleTrees = 0;
        doWork(getFileName(), 1, Boolean.FALSE);
        printMapRowsAndLoadCols();
        printMapCols();
        log("map rows: " + rows + "\n");
        log("map cols: " + cols + "\n");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                log("Looking at tree (" + i + "," + j + "): "+quadcopterMapByRows.get(i).get(j)+" ");
                if (isTreeVisible(i, j)) {
                    log(" visible\n");
                    visibleTrees++;
                } else {
                    log("\n");
                }
            }
        }
        result.setStep1(visibleTrees + "");
        result.setStep2(maxScenicScore + "");
        return result;
    }

    private boolean isTreeVisible(int i, int j) {
        if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
            log(" on borders ");
            return Boolean.TRUE;
        } else {

            Integer element = quadcopterMapByRows.get(i).get(j);

            List<Integer> row = quadcopterMapByRows.get(i);
            List<Integer> leftRow = row.subList(0, j);
            List<Integer> rightRow = row.subList(j + 1, row.size());
            Integer maxLeftRow = leftRow.stream().max(Comparator.naturalOrder()).get();
            Integer maxRightRow = rightRow.stream().max(Comparator.naturalOrder()).get();
            boolean visibleOnLeftRow =  element> maxLeftRow;
            boolean visibleOnRightRow = element > maxRightRow;

            log( leftRow+" (" +element+") "+ rightRow+" ");

            List<Integer> col = quadcopterMapByCols.get(j);
            List<Integer> upCol = col.subList(0, i);
            List<Integer> downCol = col.subList(i + 1, col.size());
            Integer maxUpCol = upCol.stream().max(Comparator.naturalOrder()).get();
            Integer maxDownCol = downCol.stream().max(Comparator.naturalOrder()).get();
            boolean visibleOnUpCol = element > maxUpCol;
            boolean visibleOnDownCol = element > maxDownCol;

            log( upCol+" (" +element+") "+ downCol+" ");

            log(visibleOnLeftRow?" left ":" ");
            log(visibleOnRightRow?" right ":" ");
            log(visibleOnUpCol?" top ":" ");
            log(visibleOnDownCol?" bottom ":" ");

            int scenicScore = getScenicScore(element,i,j,upCol,downCol,leftRow,rightRow);
            if (scenicScore>maxScenicScore) {
                maxScenicScore=scenicScore;
            }

            return visibleOnLeftRow || visibleOnRightRow || visibleOnUpCol || visibleOnDownCol;

        }
    }

    private int getScenicScore(Integer element,int idxI,int idxJ,List<Integer> up,List<Integer> down,List<Integer> left,List<Integer> right) {
        int distanceLeft = 0;
        for (int i=left.size()-1;i>=0;i--) {
            distanceLeft++;
            if (left.get(i)>=element) {
                break;
            }
        }
        int distanceRight = 0;
        for (int i=0;i<right.size();i++) {
            distanceRight++;
            if (right.get(i)>=element) {
                break;
            }
        }
        int distanceTop = 0;
        for (int i=up.size()-1;i>=0;i--) {
            distanceTop++;
            if (up.get(i)>=element) {
                break;
            }
        }
        int distanceBottom = 0;
        for (int i=0;i<down.size();i++) {
            distanceBottom++;
            if (down.get(i)>=element) {
                break;
            }
        }

        int scenicScore = distanceBottom*distanceLeft*distanceRight*distanceTop;

        log("element at "+idxI+","+idxJ+": "+element+" scenic score: ("+distanceTop+","+distanceLeft+","+distanceBottom+","+distanceRight+")="+scenicScore);
        return scenicScore;
    }

    private void printMapRowsAndLoadCols() {

        for (int i = 0; i < quadcopterMapByRows.get(0).size(); i++) {
            List<Integer> col = new ArrayList<>();
            quadcopterMapByCols.add(col);
        }

        for (int i = 0; i < quadcopterMapByRows.size(); i++) {
            for (int j = 0; j < quadcopterMapByRows.get(0).size(); j++) {
                quadcopterMapByCols.get(j).add(i, quadcopterMapByRows.get(i).get(j));
            }
        }
    }

    private void printMapCols() {
        for (List<Integer> row : quadcopterMapByCols) {
            for (Integer i : row) {
                log(i + "");
            }
            log("\n");
        }
    }
}
