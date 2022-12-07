import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day7 extends Util implements Quiz {

    Folder root=new Folder("/",0);
    Folder currentFolder = root;

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
                if (!folderName.equals("..") && !folderName.equals("/")) {
                    currentFolder = currentFolder.children.stream().filter(it -> it.name.equals(folderName)).limit(1).collect(Collectors.toList()).get(0);
                    log("moved to "+currentFolder.name);
                } else if (folderName.equals("..")) {
                    currentFolder = currentFolder.parent;
                    log("back to "+currentFolder.name);
                }
            }
        } else if (line.matches("^[0-9]+ .*")) {
            Scanner s = new Scanner(line);
            int delta = s.nextInt();
            currentFolder.incSize(delta);
            Folder parent = currentFolder.parent;
            while(parent!=null) {
                parent.incSize(delta);
                parent=parent.parent;
            }

        } else if (line.startsWith("dir ")) {
            String folderName = line.substring(4);
            Folder f = new Folder(folderName, 0);
            if (f.children.stream().noneMatch(it -> it.name.equals(folderName))) {
                currentFolder.addChild(currentFolder,f);
                log("added " + f.name + " to " + currentFolder.name);
            }
        }
    }

    public void doWorkOnLineStep2(String line) {
        log("not needed");
    }


    @Override
    public Result run() throws Exception {
        Result result = new Result();
        doWork(getFileName(), 1);
        AtomicInteger sum = new AtomicInteger(0);
        log("****** print tree *********\n");
        printTree(root,0);
        log("***************************\n");

        getSumOfFolderUnder(100000,root,sum);
        result.setStep1(sum+"");

        log("used space: "+root.size+"\n");
        log("free space: "+(70000000-root.size)+"\n");
        log("least folder size: "+(30000000-((70000000-root.size)))+"\n");

        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);

        getFolderToDelete((30000000-((70000000-root.size))),root,min);

        return result;
    }

    private void getFolderToDelete(int minSize,Folder root,AtomicInteger min) {
        if (root.size>minSize) {
            if (root.size<min.get()) {
                System.out.println("folder "+root.name+" - "+root.size+" is ok");
                min.set(root.size);
            }
        }
        for (Folder c : root.children) {
            getFolderToDelete(minSize,c,min);
        }

    }

    private void getSumOfFolderUnder(int minSize,Folder root,AtomicInteger sum) {
        if (root.size<minSize) {
            sum.addAndGet(root.size);
        }
        for (Folder c : root.children) {
            getSumOfFolderUnder(minSize,c,sum);
        }

    }

    private void printTree(Folder currentFolder,int spaces) {
        for (int i=0;i<spaces;i++) {
            System.out.print(" ");
        }
        System.out.println(currentFolder.name + " - " + currentFolder.size);
        currentFolder.children.forEach(it->printTree(it,spaces+1));
    }

    private void log(String log) {
        if (mode.equals(Mode.EXAMPLE)) {
            System.out.print(log);
        }
    }
}

class Folder {
    Folder parent=null;
    String name;
    int size;
    List<Folder> children = new ArrayList<>();

    public Folder(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void addChild(Folder parent,Folder folder) {
        folder.parent=parent;
        children.add(folder);
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
