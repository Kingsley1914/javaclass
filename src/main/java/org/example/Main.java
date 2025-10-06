// Main.java
package org.example;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Path filePath = Paths.get("autosaved.txt");
        ArrayList<String> fileArray = new ArrayList<>();
        ArrayList<String> safeList = new ArrayList<>(Collections.synchronizedList(fileArray));

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(new shellTask(1, safeList));
        executor.submit(new AutoSaveTask(2, safeList, filePath));

        while (runningState.running) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
        }

        executor.shutdownNow();
        System.out.println("Program ended cleanly.");
    }
}
