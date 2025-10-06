// AutoSaveTask.java
package org.example;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class AutoSaveTask implements Runnable {
    private final int taskId;
    private final ArrayList<String> fileArrayRef;
    private final Path filePath;

    public AutoSaveTask(int taskId, ArrayList<String> fileArrayRef, Path filePath) {
        this.taskId = taskId;
        this.fileArrayRef = fileArrayRef;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        while (runningState.running || !fileArrayRef.isEmpty()) {
            String textToAppend = null;

            synchronized (fileArrayRef) {
                if (!fileArrayRef.isEmpty()) {
                    textToAppend = fileArrayRef.remove(0);
                }
            }

            if (textToAppend != null) {
                try {
                    Files.write(filePath, (textToAppend + System.lineSeparator()).getBytes(),
                            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    System.err.println("Error writing to file: " + e.getMessage());
                }
            } else {
                try {
                    Thread.sleep(100); // avoid busy-waiting
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
        System.out.println("Auto-save task " + taskId + " ended.");
    }
}
