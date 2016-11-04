package assignment2;

import java.util.Comparator;

/**
 * Implements the Comparator interface for moves to be able to sort them.
 *
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 */
public class MoveComparator implements Comparator<Move> {
    private final Position position;
    private final Position centerPosition;

    /**
     * Constructor method.
     *
     * @param position Current knight position.
     * @param center Center position of the board.
     */
    public MoveComparator(Position position, Position center) {
        this.position = position;
        this.centerPosition = center;
    }

    /**
     * Compare two moves.
     *
     * @param a First move to compare.
     * @param b Second move to compare.
     * @return The difference between the distance to the center after applying a and b respectively to position. (a - b)
     */
    @Override
    public int compare(Move a, Move b) {
        return position.apply(a).distanceTo(centerPosition)
                - position.apply(b).distanceTo(centerPosition);
    }
}
