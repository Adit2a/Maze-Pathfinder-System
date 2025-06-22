import java.util.ArrayList;
import java.util.List;

public class Navigator {
    private int[][] navigatorMaze;
    private int rows;
    private int cols;
    
    public Navigator(Maze grid) {
        this.navigatorMaze = grid.getGridRepresentation();
        this.rows = grid.rows();
        this.cols = grid.cols();
    }
    
    public List<Point> findPath(Point start, Point end) throws PathNotFoundException {
        // IMPLEMENT FUNCTION HERE
        List<Point> ans = new ArrayList<>();
        
        if (navigatorMaze[start.x][start.y] == 1) {
            throw new PathNotFoundException();
        }
        if (navigatorMaze[end.x][end.y] == 1) {
            throw new PathNotFoundException();
        }
        
        Stack<Point> stk = new Stack<>();
        stk.push(start);
        
        while (!stk.isEmpty()) {
            Point pointer = stk.top();
            if (checkEnd(pointer, end)) {
                ans.add(0, stk.pop());
                pointer = end;
                while (stk.size() >= 1) {
                    Point cell = stk.pop();
                    if (isNeighbour(cell, pointer)) {
                        ans.add(0, cell);
                        pointer = cell;
                    }
                }
                return ans;
            }
            if (deadend(pointer, stk)) {
                stk.pop();
            }
        }
        
        throw new PathNotFoundException();
    }
    
    private boolean deadend(Point a, Stack<Point> stk) {
        boolean flag = true;
        
        if (a.x > 0 && navigatorMaze[a.x - 1][a.y] == 0) {
            stk.push(new Point(a.x - 1, a.y));
            navigatorMaze[a.x - 1][a.y] = -1;
            flag = false;
        }
        if (a.y > 0 && navigatorMaze[a.x][a.y - 1] == 0) {
            stk.push(new Point(a.x, a.y - 1));
            navigatorMaze[a.x][a.y - 1] = -1;
            flag = false;
        }
        if (a.x < rows - 1 && navigatorMaze[a.x + 1][a.y] == 0) {
            stk.push(new Point(a.x + 1, a.y));
            navigatorMaze[a.x + 1][a.y] = -1;
            flag = false;
        }
        if (a.y < cols - 1 && navigatorMaze[a.x][a.y + 1] == 0) {
            stk.push(new Point(a.x, a.y + 1));
            navigatorMaze[a.x][a.y + 1] = -1;
            flag = false;
        }
        
        return flag;
    }
    
    private boolean checkEnd(Point cell, Point end) {
        return cell.equals(end);
    }
    
    private boolean isNeighbour(Point cell1, Point cell2) {
        return Math.abs(cell1.x - cell2.x) + Math.abs(cell1.y - cell2.y) == 1;
    }
}