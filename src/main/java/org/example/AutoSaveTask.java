package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class AutoSaveTask implements Runnable {
    private final int taskId;
    private ArrayList<String> fileArrayRef = new ArrayList<String>();
    private ArrayList<String> fileArraybuffer = new ArrayList<String>();
    Path filePath;
    private runningState running;


    public AutoSaveTask(int taskId, ArrayList<String> fileArrayRef, Path filePath) {

        this.taskId = taskId;
        this.fileArrayRef = fileArrayRef;
        this.filePath = filePath;
        this.running = running;
    }

    @Override
    public void run(){
        try {
            Thread.sleep(0);
            while(true) {

                String textToAppend = this.fileArrayRef.get(0);

                try {
                    if(textToAppend != null) {
                        Files.write(this.filePath, textToAppend.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    }
                    if (this.fileArrayRef.size() > 0) {
                        this.fileArrayRef.remove(0);
                    }
//                    System.out.println("Text appended successfully!");
                } catch (IOException e) {
                    System.err.println("Error writing to file: " + e.getMessage());
                }
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted while working on Task " + taskId);
            return; // Exit early if interrupted
        }
    }
}
