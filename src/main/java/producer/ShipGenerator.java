package producer;

import lombok.Data;
import lombok.NoArgsConstructor;
import models.Ship;
import workLoadUnit.WorkLoadUnit;

import java.util.concurrent.BlockingQueue;

@Data
@NoArgsConstructor
public class ShipGenerator {

    boolean isStopped;
    private BlockingQueue tunnel = null;

    public void generateShipsToTunnel(BlockingQueue tasks) {
        int counter = 21;
        this.tunnel = tasks;
        ShipGenerator shipGenerator = new ShipGenerator();

        while (!isStopped() && counter-- > 0) {
            System.out.println("Offering new ship to unload");
            Ship ship = shipGenerator.getRandomShip();
            System.out.println(tunnel.offer(
                    new WorkLoadUnit(ship, () -> {
                        try {
                            Thread.sleep(ship.getCapacity());
                            System.out.println("Ship is unloaded: " + ship.toString()
                                    + " by thread: " + Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    })
                    )
            );
            try {
                Thread.sleep((long) (Math.random() * 200L));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Ship getRandomShip() {
        String type;
        int counter = (int) (Math.random() * 100) % 3;
        int capacity = (int) Math.round((Math.random() * 1000));

        switch (counter) {
            case 0:
                type = "Fish";
                break;
            case 1:
                type = "Oil";
                break;
            default:
                type = "Cars";
        }
        return new Ship(type, capacity);
    }
}
