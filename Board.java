package assignment2;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents the playing board.
 *
 * @author Hendrik Werner // s4549775
 * @author Jasper Haasdijk // s4449754
 */
public class Board {
    public final int width;
    public final int height;
    private final Position initialKnightPosition;
    private final Position centerPosition;
    private final int[][] visited;
    private final Move[] history;
    private Position knightPosition;

    /**
     * Constructor method.
     *
     * @param width Width of the board.
     * @param height Height of the board.
     * @param start Starting position of the knight.
     */
    public Board(int width, int height, Position start) {
        this.width = width;
        this.height = height;
        centerPosition = new Position(width / 2, height / 2);
        initialKnightPosition = start;
        knightPosition = start;
        visited = new int[width][height];
        visited[start.x][start.y] = 1;
        history = new Move[width * height - 1];
    }

    /**
     * Getter method for the knight's position.
     *
     * @return The position of the knight.
     */
    public Position getCurrentPosition() {
        return knightPosition;
    }

    /**
     * Get the position of the knight after move m has been applied.
     *
     * @param m The move to apply to the knight.
     * @return The new position of the knight after applying move m.
     */
    public Position getNewPosition(Move m) {
        return new Position(
                knightPosition.x + m.x,
                knightPosition.y + m.y
        );
    }

    /**
     * Apply move m to the board.
     *
     * @param m The move to apply.
     */
    public void apply(Move m, int step) {
        knightPosition = getNewPosition(m);
        visited[knightPosition.x][knightPosition.y] = step;
    }

    /**
     * Revert move m.
     *
     * @param m The move to revert.
     */
    public void revert(Move m) {
        visited[knightPosition.x][knightPosition.y] = 0;
        knightPosition = getNewPosition(m.invert());
    }

    /**
     * Check if position p has been visited by the knight.
     *
     * @param p The position to check.
     * @return Whether or not the knight has already visited position p.
     */
    public boolean isVisited(Position p) {
        return visited[p.x][p.y] != 0;
    }

    /**
     * Public interface for solving.
     *
     * @param open Whether or not the path is allowed to be open.
     * @return A history of moves that lead to a solution.
     */
    public Move[] solve(boolean open) {
        solve(2, width * height - 1, open);
        return history;
    }

    /**
     * Turn the board into a string.
     *
     * @return A string representing the board.
     */
    @Override
    public String toString() {
        return Arrays.stream(visited)
                .map(line -> Arrays.stream(line)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining("\t"))
                ).collect(
                        Collectors.joining("\n")
                );
    }

    /**
     * Solve the puzzle using backtracking.
     *
     * @param step Number of the current step.
     * @param notVisited Number of fields not yet visited.
     * @param open Whether the found path may be open.
     * @return Whether or not there is a solution to the current state.
     */
    private boolean solve(int step, int notVisited, boolean open) {
        if (notVisited == 0) {
            return open
                    || knightPosition.canReach(initialKnightPosition, Move.getKnightMoves());
        }
        Move[] sortedMoves = Move.getKnightMoves();
        Arrays.sort(sortedMoves, new MoveComparator(knightPosition, centerPosition).reversed());
        for (Move moveCandidate : sortedMoves) {
            if (moveCandidate.applicableTo(this)) {
                apply(moveCandidate, step);
                if (solve(step + 1, notVisited - 1, open)) {
                    history[step - 2] = moveCandidate;
                    return true;
                }
                revert(moveCandidate);
            }
        }
        return false;
    }
}
