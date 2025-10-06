// shellTask.java
package org.example;
import java.util.ArrayList;
import java.util.Scanner;

public class shellTask implements Runnable {
    private final int taskId;
    private final ArrayList<String> fileArrayRef;
    private final Scanner input = new Scanner(System.in);

    public shellTask(int taskId, ArrayList<String> fileArrayRef) {
        this.taskId = taskId;
        this.fileArrayRef = fileArrayRef;
    }

    @Override
    public void run() {
        while (runningState.running) {
            System.out.print("> ");
            String text = input.nextLine();

            if (text.equalsIgnoreCase("end")) {
                runningState.running = false;
                break;
            }

            synchronized (fileArrayRef) {
                fileArrayRef.add(text);
            }
        }
        System.out.println("Shell task " + taskId + " ended.");
    }
}
