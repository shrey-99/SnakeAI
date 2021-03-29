import java.util.ArrayList;
import java.util.Random;

public class Grid {
    private int size;
    private char grid[][];
    private char oldGrid[][];
    private Snake snake;
    private boolean apple = false;
    private int[] appleloc;
    private boolean goal;
   
    public Grid(int size, Snake snake, boolean goal) {
        this.size = size;
        this.snake = snake;
        grid = new char[size][size];
        appleloc = new int[2];
        this.goal = goal;
        generateGrid();
        oldGrid = grid.clone();
    }

    public Grid(Grid copy) {
        this.size = copy.getSize();
        char[][] cpyGrid = copy.getGrid(); 
        this.grid = new char[size][size];
        for(int i = 0; i < size; i++) {
            for(int j=0; j < size; j++) {
                grid[i][j] = cpyGrid[i][j];
            }
        }
        this.snake = new Snake(copy.getSnake());
        this.appleloc = copy.getAppleLocation();
        this.apple = copy.getApple();
        generateGrid();
        oldGrid = grid.clone();
    }

    public int getSize() {
         return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Snake getSnake() {
        return this.snake;
    }

    public char[][] getGrid() {
        return this.grid;
    }

    public int[] getAppleLocation() {
        return this.appleloc;
    }

    public boolean getApple() {
        return this.apple;
    }


    private void generateGrid() {
        for(int i = 0; i < size; i++) {
            for(int j=0; j < size; j++) {
                this.grid[i][j] = '_';
            }
        }
        generateApple(); 
        generateSnake();
    }

    private void updateGrid() {
        for(int i = 0; i < size; i++) {
            for(int j=0; j < size; j++) {
                this.grid[i][j] = '_';
            }
        }
        generateSnake();
    }

    public void printGrid() {
        generateGrid();
        System.out.println("=================================");
        for(int i = 0; i < size; i++) {
            for(int j=0; j < size; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public boolean moveSnake(char dir) {
        generateGrid();
        switch(Character.toLowerCase(dir)) {
            case 'w':    
                if(!snake.up(this.oldGrid)) {
                    //System.out.println("The head went out of bounds and died");
                    return false;
                }
                return true;
            case 's':
                if(!snake.down(this.oldGrid)) {
                    //System.out.println("The head went out of bounds and died");
                    return false;
                }
                return true;
            case 'a':
                if(!snake.left(this.oldGrid)) {
                    //System.out.println("The head went out of bounds and died");
                    return false;
                }
                return true;
            case 'd':
                if(!snake.right(this.oldGrid)) {
                    //System.out.println("The head went out of bounds and died");
                    return false;
                }
                return true;
            default:
                System.out.println("Wrong input");
                return true;
            
        }

    }

    private void generateSnake() {
        ArrayList<int[]> snakeLoc = this.snake.getLocation();  
        if(snakeLoc.size() > 1) {
            for(int i=1; i < this.snake.getLength(); i++) {
                //System.out.println(snakeLoc.get(i)[0] + " + " + snakeLoc.get(i)[1]);
                grid[snakeLoc.get(i)[0]][snakeLoc.get(i)[1]] = '#';
            }
        }
        grid[snakeLoc.get(0)[0]][snakeLoc.get(0)[1]] = 'H';
    }

    private void generateApple() {
        updateGrid();
        if(!goal) {
            if(!apple) {
                Random aRand = new Random();
                appleloc[0] = aRand.nextInt(size);
                appleloc[1] = aRand.nextInt(size);
        
                while(grid[appleloc[0]][appleloc[1]] == 'H' || grid[appleloc[0]][appleloc[1]] == '#') {
                    appleloc[0] = aRand.nextInt(size);
                    appleloc[0] = aRand.nextInt(size);
                }
            }
            grid[appleloc[0]][appleloc[1]] = '*';
            apple = true;
        }
    }

    public double calculateHeuristic() {
        //Distance from the head of the snake to the apple
        int[] sLoc = snake.getLocation().get(0);
        //Manhattan distance heuristic
        double manDist = Math.abs(sLoc[0] - appleloc[0]) + Math.abs(sLoc[1] - appleloc[1]);
        return manDist;
        //Math.sqrt(Math.pow((appleloc[0] - sLoc[0]), 2) + Math.pow((appleloc[1] - sLoc[1]), 2));
        //Math.abs(sLoc[0] - appleloc[0]) + Math.abs(sLoc[1] - appleloc[1])

    }

    public Grid getGoalState() {
        return new Grid(this.size, new Snake(1, appleloc[0], appleloc[1]), true);
    }

    public boolean isGoal() {
        int[] sLoc = snake.getLocation().get(0);
        if(sLoc[0] == appleloc[0] && sLoc[1] == appleloc[1]) {
            goal = false;
            apple = false;
            snake.increaseLength();
            generateApple();
            return true;
        }
        
        return false;
    }
}
