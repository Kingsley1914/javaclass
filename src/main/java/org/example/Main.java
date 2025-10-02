package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.example.runningState.running;

public class Main {
    public static void main(String[] args) {
        Path filePath = Paths.get("autosaved.txt");
        ;
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ArrayList<String> fileArray = new ArrayList<String>();
        executor.submit(new shellTask(1, fileArray));
        executor.submit(new AutoSaveTask(2, fileArray, filePath));
        while(true){
//            System.out.println(runningState.running);
            if (runningState.running == false){
                executor.shutdown();
                try {
                    if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                        System.out.println("Timeout reached! Forcing shutdown...");
                        executor.shutdownNow(); // Interrupt running tasks
                        break;
                    }
                } catch (InterruptedException e) {
                    executor.shutdownNow();
                }
                break;

            }
        }
    }
}