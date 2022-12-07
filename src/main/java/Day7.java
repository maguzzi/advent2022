import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Day7 extends Util implements Quiz {

    LinkedList<Folder> folderStackIn = new LinkedList<>();
    LinkedList<Folder> folderStackOut = new LinkedList<>();

    Folder currentFolder;

    Day7(Mode mode) {
        super(mode);
    }

    @Override
    public void doWorkOnLineStep1(String line) {
        if (line.startsWith("$ ")) {
            line = line.substring(2);
            Scanner s = new Scanner(line);
            String commandName = s.next();
            if (commandName.equals("cd")) {
                String folderName = s.next();
                if (folderName.equals("..")) {
                    currentFolder = folderStackIn.pop();
                    folderStackOut.push(currentFolder);
                } else {
                    currentFolder = new Folder(folderName, 0);
                    folderStackIn.push(currentFolder);
                }
                log("current folder: " + currentFolder);

            }
        } else if (line.matches("^[0-9]+ .*")) {
            Scanner s = new Scanner(line);
            int fileSize = s.nextInt();
            log("increment\n");
            folderStackIn.forEach(f -> {
                f.incSize(fileSize);
                log("\"" + f.getName() + "\" (" + fileSize + ")");
            });
        }
    }

    public void doWorkOnLineStep2(String line) {
        log("not needed");
    }


    @Override
    public Result run() throws Exception {

        Result result = new Result();
        folderStackIn.clear();
        folderStackOut.clear();
        doWork(getFileName(), 1);

        log("folderStackIn: " + folderStackIn.toString());
        log("folderStackOut: " + folderStackOut.toString());

        AtomicInteger sum = new AtomicInteger();

        folderStackIn.forEach(it -> {
            if (it.size <= 100000) {
                sum.addAndGet(it.size);
            }
        });
        folderStackOut.forEach(it -> {
            if (it.size <= 100000) {
                sum.addAndGet(it.size);
            }
        });
        result.setStep1(sum.get() + "");

/*******************************************************/

        int usedSpace = folderStackIn.getLast().size;
        log("used space = " + usedSpace);

        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);

        folderStackIn.forEach(it -> {
            if (usedSpace - it.size <= 30000000) {
                log("folder " + it.getName() + " is ok");
                if (it.getSize() <= min.get()) {
                    min.set(it.size);
                }
            }
        });

        folderStackOut.forEach(it -> {
            if (usedSpace - it.size <= 30000000) {
                log("folder " + it.getName() + " is ok");
                if (it.getSize() <= min.get()) {
                    min.set(it.size);
                }
            }
        });

        result.setStep2(min.get() + "");

        return result;
    }

    private void log(String log) {
        //if (mode.equals(Mode.EXAMPLE)) {
        System.out.print(log);
        //}
    }
}

class Folder {
    String name;
    int size;

    public Folder(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void incSize(int delta) {
        this.size += delta;
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.size;
    }

    public String toString() {
        return name + " " + size;
    }
}
