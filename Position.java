package assignment2;

import static java.lang.Math.abs;

/**
 * Represents a position (on a board).
 *
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 */
public class Position {
    public final int x;
    public final int y;
    
    /**
     * Constructor method.
     * 
     * @param x x-position
     * @param y y-position
     */
    public Position (int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Check whether the position is within the bounds of board b.
     * 
     * @param b The board that is used to determine validity.
     * @return Whether or not the position lies on board b.
     */
    public boolean laysOn (Board b) {
        return ((x < b.width)
                && (x >= 0)
                && (y < b.height)
                && (y >= 0));
    }
    
    /**
     * Check if position p has the same coordinated as this.
     * 
     * @param p Position to check for equality
     * @return Whether or not the position p has the same coordinates as this.
     */
    public boolean equals (Position p) {
        return (this.x == p.x
                && this.y == p.y);
    }
    
    /**
     * Check if position p can be reached from this with one of the moves.
     * 
     * @param p Position that this tried to reach.
     * @param moves The moves this uses to reach p.
     * @return Whether or not position p can be reached from this using one of the moves.
     */
    public boolean canReach(Position p, Move[] moves) {
        for (Move m: moves) {
            if (new Position(this.x + m.x, this.y + m.y).equals(p)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Calculate the distance between two positions.
     * 
     * @param p Position to calculate the distance to.
     * @return The distance to position p.
     */
    public int distanceTo (Position p) {
        return (abs(this.x - p.x)
                + abs(this.y - p.y));
    }
    
    /**
     * Turn the position into a string.
     * 
     * @return A string containing relevant information about the position.
     */
    @Override
    public String toString () {
        return String.format("<Position: (%s, %s)>", x, y);
    }
}
