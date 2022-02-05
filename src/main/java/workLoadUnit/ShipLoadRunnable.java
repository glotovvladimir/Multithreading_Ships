package workLoadUnit;

import lombok.Data;

import java.util.concurrent.BlockingQueue;

@Data
public class ShipLoadRunnable implements Runnable {

    private Thread thread = null;
    private BlockingQueue tasks;
    private boolean isStopped;

    public ShipLoadRunnable(BlockingQueue<WorkLoadUnit> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        String threadName;

        setThread(Thread.currentThread());
        threadName = getThread().getName();
        while (!isStopped) {
            if (tasks.size() > 0) {
                try {
                    // Checking if load is suitable for thread
                    if (threadName.contains(((WorkLoadUnit) tasks.peek()).getShip().getType())) {
                        System.out.println(Thread.currentThread().getName() + " is starting task");
                        ((WorkLoadUnit) tasks.take()).getAction().run();
                        System.out.println();
                    } else {
                        System.out.println("No work for " + threadName + ", waiting...");
                        Thread.sleep(300);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void doStop() {
        setStopped(true);
        getThread().interrupt();
    }
}
