package assignment2;

import static java.lang.Math.abs;

/**
 * Represents two dimensional moves.
 *
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 */
public class Move {
    public final int x;
    public final int y;
    private static final Move[] KNIGHT_MOVES = new Move[8];
    private static boolean knightMovesGenerated = false;
    
    /**
     * Constructor method.
     * 
     * @param moveX Movement in the x direction (horizontally).
     * @param moveY Movement in the y direction (vertically).
     */
    public Move (int moveX, int moveY) {
        this.x = moveX;
        this.y = moveY;
    }
    
    public static void printMoves(Move[] moves) {
        int step = 1;
        for (Move m: moves) {
            System.out.println(String.format("Step %2d: %s", step++, m));
        }
    }
    
    /**
     * Determine the validity of a move.
     * 
     * @return Whether the move is a valid knight's move.
     */
    public boolean isKnightMove () {
        return (abs(x) + abs(y) == 3
                && (abs(x) < 3)
                && (abs(y) < 3));
    }
    
    /**
     * Get all possible knight's moves from cache or calculate them.
     * 
     * @return A set of all valid knight's moves.
     */
    public static Move[] getKnightMoves () {
        if (!knightMovesGenerated) {
            generateKnightMoves();
        }
        return KNIGHT_MOVES;
    }
    
    /**
     * Check if the move is applicable to board b.
     * 
     * @param b Board for which to check applicability.
     * @return Whether or not the move is applicable to board b.
     */
    public boolean applicableTo (Board b) {
        Position candidatePosition = b.getNewPosition(this);
        return (candidatePosition.laysOn(b)
                && !b.isVisited(candidatePosition));
    }
    
    public Move invert () {
        return new Move(-this.x, -this.y);
    }
    
    /**
     * Turn the move into a string.
     * 
     * @return A string containing relevant information about the move.
     */
    @Override
    public String toString () {
        return String.format("<Move: (%2s, %2s)>", x, y);
    }
    
    /**
     * Calculate all valid knight's moves and cache them.
     */
    private static void generateKnightMoves () {
        int pos = 0;
        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {
                Move candidate = new Move(x, y);
                if (candidate.isKnightMove()) {
                    KNIGHT_MOVES[pos++] = candidate;
                }
            }
        }
        knightMovesGenerated = true;
    }
}
