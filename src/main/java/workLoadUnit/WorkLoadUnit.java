package workLoadUnit;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.Ship;

@Data
@AllArgsConstructor
public class WorkLoadUnit {

    private Ship ship;
    private Runnable action;
}
