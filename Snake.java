import java.util.ArrayList;

public class Snake {
    private int length;
    private ArrayList<int[]> location = new ArrayList<int[]>();
    private boolean status;

    public Snake(int length, int row, int col) {
        this.length = length;
        this.status = true;
        int[] loc = new int[2];
        loc[0] = row;
        loc[1] = col;
        location.add(loc);
    }

    public Snake(Snake copy) {
        this.length = copy.getLength();
        ArrayList<int[]> tempcpy = copy.getLocation();
        for(int i=0 ; i < tempcpy.size(); i++) {
            int[] loc = new int[2];
            loc[0] = tempcpy.get(i)[0];
            loc[1] = tempcpy.get(i)[1];
            location.add(loc);
        }
        this.status = copy.getStatus();
        
    }

    public ArrayList<int[]> getLocation() {
        //int[] i = location.toArray();
        return location;
    }

    public int getLength() {
        return this.length;
    }

    public boolean getStatus() {
        return this.status;
    }

    public boolean up(char[][] grid) {
        int[] newLoc = new int[2];
        if(location.get(0)[0]-1 >= 0) {
            newLoc[0] = location.get(0)[0]-1;
            newLoc[1] = location.get(0)[1];

            if(grid[newLoc[0]][newLoc[1]] == '#') {
                this.status = false;
                return this.status;
            }
            
            location.add(0, newLoc);
            location.remove(length);
            return this.status;

        }
        else {
            this.status = false;
            return this.status;
        }
    }

    public boolean down(char[][] grid) {
        int[] newLoc = new int[2];
        if(location.get(0)[0]+1 < Constants.SIZE) {
            newLoc[0] = location.get(0)[0]+1;
            newLoc[1] = location.get(0)[1];

            if(grid[newLoc[0]][newLoc[1]] == '#') {
                this.status = false;
                return this.status;
            }
            
            location.add(0, newLoc);
            location.remove(length);
            return this.status;
        }
        else {
            this.status = false; 
            return this.status;
        }
    }

    public boolean right(char[][] grid) {
        int[] newLoc = new int[2];
        if(location.get(0)[1]+1 < Constants.SIZE) {
            newLoc[0] = location.get(0)[0];
            newLoc[1] = location.get(0)[1]+1;

            if(grid[newLoc[0]][newLoc[1]] == '#') {
                this.status = false;
                return this.status;
            }
            
            location.add(0, newLoc);
            location.remove(length);
            return this.status;
        }
        else {
            this.status = false;
            return this.status;
        }
    }

    public boolean left(char[][] grid) {
        int[] newLoc = new int[2];
        if(location.get(0)[1]-1 >= 0) {
            newLoc[0] = location.get(0)[0];
            newLoc[1] = location.get(0)[1]-1;

            if(grid[newLoc[0]][newLoc[1]] == '#') {
                this.status = false;
                return this.status;
            }
            
            location.add(0, newLoc);
            location.remove(length);
            return this.status;
        }
        else {
            this.status = false;
            return this.status;
        }
    }

    public void increaseLength() {
        int[] incLen = location.get(length-1).clone();
        if(incLen[0] + 1 < Constants.SIZE) {
            incLen[0]++;
        }
        else if(incLen[0] - 1 > 0) {
            incLen[0]--;
        }
        location.add(incLen);
        this.length++;

    }
}
