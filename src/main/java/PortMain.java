import producer.ShipGenerator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PortMain {

    public static void main(String[] args) {
        
        int amount = 15;
        ShipGenerator shipGenerator = new ShipGenerator();
        BlockingQueue tasks = new ArrayBlockingQueue(amount);
        PortConsumersPool portConsumersPool = new PortConsumersPool(tasks);

        shipGenerator.generateShipsToTunnel(tasks);
        portConsumersPool.waitAllTasksExecuted();
        portConsumersPool.doStop();

    }
}
