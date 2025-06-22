public class Maze {
    private int[][] gridRepresentation;
    
    public Maze(int m, int n) {
        // DO NOT MODIFY THIS
        // We initialise the array with all 0s, as initially all cells are vacant
        gridRepresentation = new int[m][n];
        for (int row = 0; row < m; row++) {
            for (int column = 0; column < n; column++) {
                gridRepresentation[row][column] = 0;
            }
        }
    }
    
    public void addGhost(int x, int y) {
        // IMPLEMENT YOUR FUNCTION HERE
        gridRepresentation[x][y] = 1;
    }
    
    public void removeGhost(int x, int y) {
        // IMPLEMENT YOUR FUNCTION HERE
        gridRepresentation[x][y] = 0;
    }
    
    public boolean isGhost(int x, int y) {
        // IMPLEMENT YOUR FUNCTION HERE
        return gridRepresentation[x][y] == 1;
    }
    
    public void printGrid() {
        // IMPLEMENT YOUR FUNCTION HERE
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols(); j++) {
                System.out.print(gridRepresentation[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public int rows() {
        return gridRepresentation.length;
    }
    
    public int cols() {
        return gridRepresentation[0].length;
    }
    
    public int[][] getGridRepresentation() {
        return gridRepresentation;
    }
}