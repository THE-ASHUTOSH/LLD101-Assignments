package event_ticketing;

public class Seat {
    private final String seatId;
    private final int row;
    private final int col;
    private final PricingTier tier;

    public Seat(String seatId, int row, int col, PricingTier tier) {
        this.seatId = seatId;
        this.row = row;
        this.col = col;
        this.tier = tier;
    }

    public String getSeatId() { return seatId; }
    public int getRow() { return row; }
    public int getCol() { return col; }
    public PricingTier getTier() { return tier; }
}
