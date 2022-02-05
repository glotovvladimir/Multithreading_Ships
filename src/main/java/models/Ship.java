package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ship {

    private final String type;
    private final int capacity;

//    public Ship(String type, int capacity) {
//        this.type = type;
//        this.capacity = capacity;
//    }

    @Override
    public String toString() {
        return "type: " + getType() + " of capacity: " + getCapacity();
    }
}
