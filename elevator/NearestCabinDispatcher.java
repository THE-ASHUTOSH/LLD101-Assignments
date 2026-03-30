package elevator;

import java.util.List;

public class NearestCabinDispatcher implements LiftDispatchStrategy {

    @Override
    public LiftCabin determineBestCabin(List<LiftCabin> cabins, int requestedLevel, TravelDirection direction) {
        LiftCabin optimalCabin = null;
        int minimumDistance = Integer.MAX_VALUE;

        for (LiftCabin cabin : cabins) {
            if (cabin.getStatus() == LiftStatus.OUT_OF_ORDER) {
                continue;
            }

            int distance = Math.abs(cabin.getCurrentLevel() - requestedLevel);
            
            if (isEligible(cabin, requestedLevel, direction)) {
                if (distance < minimumDistance) {
                    minimumDistance = distance;
                    optimalCabin = cabin;
                }
            }
        }

        return optimalCabin;
    }

    private boolean isEligible(LiftCabin cabin, int requestedLevel, TravelDirection direction) {
        if (cabin.getStatus() == LiftStatus.IDLE) {
            return true;
        }
        
        if (cabin.getStatus() == LiftStatus.MOVING_UP && direction == TravelDirection.ASCENDING && cabin.getCurrentLevel() <= requestedLevel) {
            return true;
        }
        
        if (cabin.getStatus() == LiftStatus.MOVING_DOWN && direction == TravelDirection.DESCENDING && cabin.getCurrentLevel() >= requestedLevel) {
            return true;
        }
        
        return false;
    }
}
