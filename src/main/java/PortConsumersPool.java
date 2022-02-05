import lombok.Data;
import lombok.SneakyThrows;
import workLoadUnit.ShipLoadRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

@Data
public class PortConsumersPool {

    private BlockingQueue tasks;
    private List<ShipLoadRunnable> shipLoadRunnables = new ArrayList<>();
    boolean isStopped;

    public PortConsumersPool(BlockingQueue tasks) {
        this.tasks = tasks;

        for (int i = 0; i < 3; i++) {
            shipLoadRunnables.add(new ShipLoadRunnable(tasks));
        }

        Thread thread_1 = new Thread(shipLoadRunnables.get(0));
        Thread thread_2 = new Thread(shipLoadRunnables.get(1));
        Thread thread_3 = new Thread(shipLoadRunnables.get(2));

        thread_1.setName("Cars thread");
        thread_2.setName("Oil thread");
        thread_3.setName("Fish thread");

        thread_1.start();
        thread_2.start();
        thread_3.start();
    }

    @SneakyThrows
    public void waitAllTasksExecuted() {
        while (tasks.size() > 0) {
            Thread.sleep(100);
        }
    }

    @SneakyThrows
    public void doStop() {
        // Timeout used for threads to complete already taken task
        Thread.sleep(3000);
        setStopped(true);
        for (ShipLoadRunnable slr : shipLoadRunnables) {
            slr.doStop();
        }
    }
}
