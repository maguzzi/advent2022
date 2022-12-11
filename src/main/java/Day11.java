import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day11 extends Util implements Quiz {

    int round = 1;

    Day11(Mode mode) {
        super(mode);
    }

    String status = "";
    List<Monkey> monkeys = new ArrayList<>();
    Monkey monkey;

    @Override
    public void doWorkOnLineStep1(String line) {
        switch (status) {
            case "":
                monkey = new Monkey();
                setMonkeyIndex(line);
                monkeys.add(monkey);
                status = "STARTING_ITEMS";
                break;
            case "STARTING_ITEMS":
                setStartingItems(line);
                status = "OPERATION";
                break;
            case "OPERATION":
                setOperation(line);
                status = "TEST";
                break;
            case "TEST":
                setTest(line);
                status = "TEST_TRUE";
                break;
            case "TEST_TRUE":
                setTestTrue(line);
                status = "TEST_FALSE";
                break;
            case "TEST_FALSE":
                setTestFalse(line);
                status = "EMPTY_LINE";
                break;
            case "EMPTY_LINE":
                status = "";
                break;
            default:
                break;
        }

    }


    private void setMonkeyIndex(String line) {
        Scanner s = new Scanner(line);
        s.findInLine("Monkey (\\d+):");
        monkey.setIdx(Integer.parseInt(s.match().group(1)));
    }

    private void setStartingItems(String line) {
        Scanner s = new Scanner(line);
        s.findInLine(" +Starting items: (.*)");
        String itemString = s.match().group(1);
        String[] itemStrings = itemString.split(",");
        for (int i = 0; i < itemStrings.length; i++) {
            monkey.getItems().addLast(new BigInteger(itemStrings[i].trim()));
        }
    }

    private void setOperation(String line) {
        Scanner s = new Scanner(line);
        s.findInLine(" +Operation: new = (.*)");
        String expression = s.match().group(1);
        monkey.setOperationString(expression);
    }

    private void setTest(String line) {
        Scanner s = new Scanner(line);
        s.findInLine(" +Test: divisible by (\\d+)");
        monkey.setTest(new BigInteger(s.match().group(1)));
    }

    private void setTestTrue(String line) {
        Scanner s = new Scanner(line);
        s.findInLine(" +If true: throw to monkey (\\d+)");
        monkey.setToMonkeyIdxIfTrue(Integer.parseInt(s.match().group(1)));
    }

    private void setTestFalse(String line) {
        Scanner s = new Scanner(line);
        s.findInLine(" +If false: throw to monkey (\\d+)");
        monkey.setToMonkeyIdxIfFalse(Integer.parseInt(s.match().group(1)));
    }

    public void doWorkOnLineStep2(String line) {

    }


    @Override
    public Result run() throws Exception {
        Result result = new Result();

        doWork(getFileName(), 1, Boolean.FALSE);


        /*for (int i = 0; i < monkeys.size(); i++) {
            log(monkeys.get(i).toString()+"\n");
            log("\n");
        }*/

        while (round <= 20) {
            log(String.format("***** Round %d ******\n", round));
            for (int i = 0; i < monkeys.size(); i++) {
                Monkey monkey = monkeys.get(i);
                //log(String.format("Monkey %d:\n", monkey.getIdx()));
                while (!monkey.getItems().isEmpty()) {
                    monkey.incrementInspections();
                    BigInteger item = monkey.getItems().removeFirst();
                    //log(String.format("  Monkey inspects an item with a worry level of %d.\n", item));
                    BigInteger newV = monkey.evaluate(item);
                    //log(String.format("    Worry level is (%s) to: %d.\n", monkey.getOperationString(), newV));

                    BigInteger newWorryLevel = newV.divide(new BigInteger("3"));


                    //log(String.format("    Monkey gets bored with item. Worry level is divided by 3 to %d.\n",newWorryLevel));

                    boolean test = newWorryLevel.remainder(monkey.getTest()).equals(BigInteger.ZERO);
                    if (test) {
                        //log(String.format("    Current worry level is divisible by %d.\n", monkey.getTest()));
                        //log(String.format("    Item with worry level %d is thrown to monkey %d.\n", newWorryLevel, monkey.getToMonkeyIdxIfTrue()));
                        monkeys.get(monkey.getToMonkeyIdxIfTrue()).getItems().addLast(newWorryLevel);
                    } else {
                        //log(String.format("    Current worry level is not divisible by %d.\n", monkey.getTest()));
                        //log(String.format("    Item with worry level %d is thrown to monkey %d.\n", newWorryLevel, monkey.getToMonkeyIdxIfFalse()));
                        monkeys.get(monkey.getToMonkeyIdxIfFalse()).getItems().addLast(newWorryLevel);
                    }
                }
            }
            /*for (int i = 0; i < monkeys.size(); i++) {
                log(String.format("Monkey %d-%d: (%s) %s\n", i, monkeys.get(i).getIdx(), monkeys.get(i).getInspections().toString(), monkeys.get(i).getItems().toString()));
            }*/

            round++;
        }


        List<BigInteger> activeness = monkeys.stream().map(Monkey::getInspections).collect(Collectors.toList());

        log("\nactiveness: " + activeness.toString() + "\n");

        List<BigInteger> collect = activeness.stream().sorted().collect(Collectors.toList());
        BigInteger firstMax = collect.get(collect.size() - 1);
        BigInteger secondMax = collect.get(collect.size() - 2);

        result.setStep1(firstMax.multiply(secondMax) + "");
        return result;
    }

}

class Monkey {
    private int idx;
    private LinkedList<BigInteger> items = new LinkedList<>();
    private String operationString;
    private BigInteger test;
    private Integer toMonkeyIdxIfTrue;
    private Integer toMonkeyIdxIfFalse;
    private BigInteger inspections = BigInteger.ZERO;

    public String toString() {
        return String.format("Monkey %d (inspections: %f):\n" +
                "  Starting items: %s\n" +
                "  Operation: %s\n" +
                "  Test: divisible by %s\n" +
                "    If true: throw to monkey %d\n" +
                "    If false: throw to monkey %d\n", idx, inspections, items.toString(), operationString, test.toString(), toMonkeyIdxIfTrue, toMonkeyIdxIfFalse);
    }

    public void incrementInspections() {
        inspections=inspections.add(BigInteger.ONE);
    }

    public BigInteger getInspections() {
        return this.inspections;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public LinkedList<BigInteger> getItems() {
        return items;
    }

    public String getOperationString() {
        return operationString;
    }

    public void setOperationString(String operationString) {
        this.operationString = operationString;
    }

    public BigInteger getTest() {
        return test;
    }

    public void setTest(BigInteger test) {
        this.test = test;
    }

    public Integer getToMonkeyIdxIfTrue() {
        return toMonkeyIdxIfTrue;
    }

    public void setToMonkeyIdxIfTrue(Integer toMonkeyIdxIfTrue) {
        this.toMonkeyIdxIfTrue = toMonkeyIdxIfTrue;
    }

    public Integer getToMonkeyIdxIfFalse() {
        return toMonkeyIdxIfFalse;
    }

    public void setToMonkeyIdxIfFalse(Integer toMonkeyIdxIfFalse) {
        this.toMonkeyIdxIfFalse = toMonkeyIdxIfFalse;
    }

    public BigInteger evaluate(BigInteger old) {
        Scanner s = new Scanner(operationString);
        BigInteger v = BigInteger.ZERO;
        String n = s.next();
        if (n.equals("old")) {
            v = old;
        }
        String operator = s.next();
        String operandS = s.next();
        BigInteger operand;
        if (operandS.equals("old")) {
            operand = old;
        } else {
            operand = new BigInteger(operandS);
        }
        switch (operator) {
            case "+":
                return v.add(operand);
            case "-":
                return v.subtract(operand);
            case "*":
                return v.multiply(operand);
            case "/":
                return v.divide(operand);
            default:
                return BigInteger.ZERO;
        }
    }
}