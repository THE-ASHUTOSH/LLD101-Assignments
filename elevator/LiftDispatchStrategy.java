package elevator;

import java.util.List;

public interface LiftDispatchStrategy {
    LiftCabin determineBestCabin(List<LiftCabin> cabins, int requestedLevel, TravelDirection direction);
}
