import java.util.ArrayList;

public class Node {
    public Grid data;
    public ArrayList<Node> children;
    public double h;
    public double g;
    public double f;
    public Node(Grid grid){
        this.data = grid;
        children = new ArrayList<Node>();
    }

    public void setHeuristic() {
        this.h = this.data.calculateHeuristic();
    }

    public void print() {
        //System.out.println("h: " + h);
        //System.out.println("g: " + g);
        //System.out.println("f: " + f);
        data.printGrid();
    }

    public ArrayList<Node> generateSuccessors() {
        Node upSuc = new Node(new Grid(data));
        if(upSuc.data.moveSnake('w')) {
            upSuc.g = this.g+1;
            children.add(upSuc);
        }
        else {
            //System.out.println("Dead scenario");
        }

        Node downSuc = new Node(new Grid(data));
        if(downSuc.data.moveSnake('s')) {
            downSuc.g = this.g+1;
            children.add(downSuc);
        }
        else {
            //System.out.println("Dead scenario");
        }

        Node leftSuc = new Node(new Grid(data));
        if(leftSuc.data.moveSnake('a')) {
            leftSuc.g = this.g+1;
            children.add(leftSuc);
        }
        else {
            //System.out.println("Dead scenario");
        }

        Node rightSuc = new Node(new Grid(data));
        if(rightSuc.data.moveSnake('d')) {
            rightSuc.g = this.g+1;
            children.add(rightSuc);
        }
        else {
            //System.out.println("Dead scenario");
        }

        return children;
    }

    public boolean checkGoal() {
        if(this.data.isGoal()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void reset() {
        this.g = 0;
        this.f = this.g + this.h;
        children.clear();
    }
}
