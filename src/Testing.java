import dataStructures.BSTree;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by martin on 12/10/16.
 */
public class Testing {
    private static ArrayList<Integer> arrayList;
    private static BSTree<Integer> bst; // binary search tree
    private static TreeSet<Integer> treeSet;
    private static ChoiceMenu CHOICE_MENU;

    static {
        CHOICE_MENU = new ChoiceMenu();
        CHOICE_MENU.addToMenu("contains");
        CHOICE_MENU.addToMenu("sort");
    }




    public static void init(int sampleSize) {
        bst = new BSTree<>();
        treeSet = new TreeSet<>();
        arrayList = new ArrayList<>(sampleSize);

        // Generate numbers and shuffle them
        for (int i = 1; i <= sampleSize; i++) arrayList.add(i);
        Collections.shuffle(arrayList);

        // fill other containers
        arrayList.forEach(i -> {
            bst.insert(i);
            treeSet.add(i);
        });
    }

    public static void testContains(int sampleSize) {
        init(sampleSize);
        setHeader("contains", sampleSize);

        long startTime = System.nanoTime();
        arrayList.contains(5498);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.format("%-16s  %15d  %13s\n", "ArrayList", elapsedTime, "O(N)");

        startTime = System.nanoTime();
        bst.contains(5498);
        elapsedTime = System.nanoTime() - startTime;
        System.out.format("%-16s  %15d  %13s\n", "BinarySearchTree", elapsedTime, "O(logN)");

        startTime = System.nanoTime();
        treeSet.contains(5498);
        elapsedTime = System.nanoTime() - startTime;
        System.out.format("%-16s  %15d  %13s\n", "TreeSet", elapsedTime, "O(logN)");

        System.out.println();
    }

    public static void testSort(int sampleSize) {
        init(sampleSize);
        setHeader("sort", sampleSize);

        long startTime = System.nanoTime();
        arrayList.sort((i1, i2) -> i1.compareTo(i2)); // internally uses merge sort or quick sort
        long elapsedTime = System.nanoTime() - startTime;
        System.out.format("%-16s %15d %13s\n", "ArrayList", elapsedTime, "O(N*logN)");

        startTime = System.nanoTime();
        bst.getSorted();
        elapsedTime = System.nanoTime() - startTime;
        System.out.format("%-16s %15d %13s\n", "BinarySearchTree", elapsedTime, "O(N)");

        System.out.println();
    }

    private static void setHeader(String methodName, int sampleSize) {
        System.out.format("=== TESTING %s ===\n", methodName);
        System.out.println("Sample size : " + sampleSize);
        System.out.println();
        System.out.format("%-16s  %15s  %13s\n", "Data Type", "Time (ns)", "Complexity");
        System.out.println("-------------------------------------------------");
    }



    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int index;
            CHOICE_MENU.show();
            String input = scanner.next();
            if (input.equals("q"))
                break;
            else
                index = Integer.parseInt(input);
            System.out.print("Insert sample size: ");
            int sampleSize = scanner.nextInt();

            String methodName = CHOICE_MENU.getMethodName(index);
            Method method = Testing.class.getMethod(methodName, int.class);
            method.invoke(Testing.class, sampleSize);
        }
    }
}

class ChoiceMenu {
    private StringBuilder menu;
    private int menuOptionNumber;

    public ChoiceMenu() {
        menuOptionNumber = 1;
        menu = new StringBuilder();
        menu.append("Choose method to test: (Press 'q' to exit)");
        menu.append("\n");
    }

    public void addToMenu(String methodName) {
        menu.append(menuOptionNumber++);
        menu.append(" - ");
        menu.append(methodName);
        menu.append("\n");
    }

    public String toString() {
        return menu.toString();
    }

    public void show() {
        System.out.println(toString());
    }

    public String getMethodName(int index) {
        String resName = "";
        String[] lines = toString().split("\n");
        for (String line : lines) {
            String[] parts = line.split("\\s+");
            if (parts[0].equals(String.valueOf(index))) {
                String tmpName = parts[parts.length - 1];
                String firstLetter = tmpName.substring(0, 1).toUpperCase();
                resName =  "test" + firstLetter + tmpName.substring(1, tmpName.length());
                break;
            }
        }
        return resName;
    }
}
