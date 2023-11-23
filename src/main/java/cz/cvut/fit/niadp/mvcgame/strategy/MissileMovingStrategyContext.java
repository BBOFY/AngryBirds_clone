package cz.cvut.fit.niadp.mvcgame.strategy;

import java.util.ArrayList;
import java.util.List;

public class MissileMovingStrategyContext {

    private IMovingStrategy movingStrategy;
    private final List<IMovingStrategy> movingStrategies = new ArrayList<>();
    private int movingStrategySelector = 0;

    public MissileMovingStrategyContext() {
//        this.movingStrategies.add(new SimpleMovingStrategy());
//        this.movingStrategies.add(new RealisticMovingStrategy());
//        this.movingStrategies.add(new FallingMovingStrategy());
        this.movingStrategies.add(new SplitMovingStrategy());
        movingStrategy = movingStrategies.get(0);
    }

    public IMovingStrategy getStrategy() {
        return movingStrategy;
    }

    public void toggleMovingStrategy() {

        movingStrategySelector += 1;
        if (movingStrategySelector >= movingStrategies.size()) {
            movingStrategySelector = 0;
        }
        movingStrategy = movingStrategies.get(movingStrategySelector);
    }

}
