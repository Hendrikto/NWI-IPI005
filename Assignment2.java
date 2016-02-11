package assignment2;

/**
 * This is the main class.
 *
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 */
public class Assignment2 {
    private static final int BOARD_WIDTH = 6;
    private static final int BOARD_HEIGHT = 6;
    
    /**
     * Entry point.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b = new Board(BOARD_WIDTH, BOARD_HEIGHT, new Position(0, 0));
        System.out.println("Solution:");
        Move.printMoves(b.solve());
        System.out.println(b);
    }
}
