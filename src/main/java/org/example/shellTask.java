package org.example;
import java.util.Scanner;
import java.util.ArrayList;

public class shellTask implements Runnable {
    private final int taskId;
    private ArrayList<String> fileArrayRef = new ArrayList<String>();
    private Scanner input = new Scanner(System.in);
    private runningState running;

    public shellTask(int taskId, ArrayList<String> fileArrayRef) {

        this.taskId = taskId;
        this.fileArrayRef = fileArrayRef;
        this.running = running;
    }

    @Override
    public void run(){
        try {
            Thread.sleep(0); // Simulate long-running work
            while (true){
                System.out.print("> ");

                String text = input.nextLine();
                if (text.equals("end")){
                    runningState.running= false;
                }
                System.out.println(runningState.running);
                this.fileArrayRef.add(text);
//                System.out.println(text);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted while working on Task " + taskId);
            return; // Exit early if interrupted
        }
    }
}

