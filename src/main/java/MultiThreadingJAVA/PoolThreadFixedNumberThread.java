package MultiThreadingJAVA;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

//https://fr.tutorialcup.com/java/thread-pool-in-java.htm
class PoolThreadFixedNumberThread implements Runnable {
    private String name;

    public PoolThreadFixedNumberThread(String name) {
        this.name = name;
    }

    public String getTaskName() {
        return name;
    }

    //Task
    public void run() {
        try {
            System.out.println("Executing Task: " + name);
            int rand = (int)(Math.random()*15);
            System.out.println("Random value for " + name + ": " + rand);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

}
class FixedThreadPoolDemo {
    public static void main(String[] args) {
        //Create a thread pool with 3 threads
        ThreadPoolExecutor ex = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

        //Execute 5 tasks using 3 threads
        for(int i=1;i<=5;i++) {
            PoolThreadFixedNumberThread t = new PoolThreadFixedNumberThread("Task " + i);
            System.out.println("Task started: "+ t.getTaskName());
            ex.execute(t);
        }
        ex.shutdown();
    }
}
