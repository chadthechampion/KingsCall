package kingscall.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The four seats around the table, in clockwise play order.
 *
 * <p>{@link #SOUTH} is the human player. South/North are one partnership,
 * East/West the other. Each seat keeps the legacy id string
 * ({@code "player 1"} .. {@code "player 4"}) that the rest of the game and the
 * saved artwork still key off.
 */
public enum Seat {
    SOUTH("player 1", Team.HUMAN),
    EAST("player 2", Team.OPPONENT),
    NORTH("player 3", Team.HUMAN),
    WEST("player 4", Team.OPPONENT);

    private final String id;
    private final Team team;

    Seat(String id, Team team) {
        this.id = id;
        this.team = team;
    }

    /** Legacy identifier, e.g. {@code "player 1"}. */
    public String id() {
        return id;
    }

    public Team team() {
        return team;
    }

    public boolean isHuman() {
        return this == SOUTH;
    }

    /** The partner sharing this seat's team. */
    public Seat partner() {
        return switch (this) {
            case SOUTH -> NORTH;
            case NORTH -> SOUTH;
            case EAST -> WEST;
            case WEST -> EAST;
        };
    }

    /** The other three seats, starting with the one immediately after this one and continuing clockwise. */
    public List<Seat> othersInPlayOrder() {
        List<Seat> order = new ArrayList<>();
        Seat[] seats = values();
        for (int step = 1; step < seats.length; step++) {
            order.add(seats[(ordinal() + step) % seats.length]);
        }
        return order;
    }

    public static Seat fromId(String id) {
        for (Seat seat : values()) {
            if (seat.id.equals(id)) {
                return seat;
            }
        }
        throw new IllegalArgumentException("Unknown seat id: " + id);
    }
}
