package assignment2;

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
        return ((this.x == p.x)
                && this.y == p.y);
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
