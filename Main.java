import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    
    public static boolean isValid(int x, int y, int rows, int cols) {
        if (x < 0 || x >= rows) {
            System.out.println("The row of cell (" + x + ", " + y + ") is out of bounds, hence this path is invalid.");
            return false;
        } else if (y < 0 || y >= cols) {
            System.out.println("The column of cell (" + x + ", " + y + ") is out of bounds, hence this path is invalid.");
            return false;
        }
        return true;
    }
    
    public static boolean isNeighbour(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1) == 1;
    }
    
    public static void main(String[] args) {
        
        // YOU CAN TWEAK THESE PARAMETERS IN ORDER TO GENERATE MORE TESTCASES
        int gridRows = 4;
        int gridCols = 4;
        Point[] ghosts = {new Point(0, 1), new Point(2, 2), new Point(3, 1)};
        Point startPoint = new Point(2, 0);
        Point endPoint = new Point(2, 3);
        
        // This is where the checker logic starts
        Maze sampleGrid = new Maze(gridRows, gridCols);
        
        for (Point ghost : ghosts) {
            sampleGrid.addGhost(ghost.x, ghost.y);
            if (!sampleGrid.isGhost(ghost.x, ghost.y)) {
                System.out.println("The cell " + ghost + " is supposed to contain a ghost, but your program says otherwise!\nTESTCASE FAILED");
                System.exit(1);
            }
        }
        
        sampleGrid.printGrid();
        /*
        EXPECTED OUTPUT : 
        0 1 0 0
        0 0 0 0
        0 0 1 0
        0 1 0 0
        */
        
        Navigator pacManInstance = new Navigator(sampleGrid);
        
        try {
            List<Point> path = pacManInstance.findPath(startPoint, endPoint);
            boolean isPathValid = true;
            
            if (!path.get(0).equals(startPoint)) {
                System.out.println("The path is supposed to begin with the point " + startPoint + ", hence this path is invalid.");
                isPathValid = false;
            }
            
            if (!path.get(path.size() - 1).equals(endPoint)) {
                System.out.println("The path is supposed to end with the point " + endPoint + ", hence this path is invalid.");
                isPathValid = false;
            }
            
            Set<Point> allCells = new HashSet<>();
            for (Point cell : path) {
                if (isValid(cell.x, cell.y, gridRows, gridCols) && 
                    sampleGrid.getGridRepresentation()[cell.x][cell.y] == 1) {
                    System.out.println("The cell " + cell + " that you have in your path is not vacant, hence this path is invalid.");
                    isPathValid = false;
                }
                if (allCells.contains(cell)) {
                    System.out.println("The cell " + cell + " that you have in your path is a duplicate cell, hence this path is invalid.");
                    isPathValid = false;
                }
                allCells.add(cell);
            }
            
            for (int i = 0; i < path.size() - 1; i++) {
                if (!isNeighbour(path.get(i).x, path.get(i).y, path.get(i + 1).x, path.get(i + 1).y)) {
                    System.out.println("Cells " + path.get(i) + " and " + path.get(i + 1) + " are not neighbours, hence this path is invalid.");
                    isPathValid = false;
                }
            }
            
            if (isPathValid) {
                System.out.println("PATH FOUND SUCCESSFULLY!");
            } else {
                System.out.println("TESTCASE FAILED");
            }
            
        } catch (PathNotFoundException e) {
            System.out.println("TESTCASE FAILED. A VALID PATH DOES EXIST BETWEEN THESE TWO LOCATIONS");
        }
    }
}