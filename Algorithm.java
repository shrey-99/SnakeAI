import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class Algorithm {
    //public Node head;
    static int score = 0;
    public static void main(String[] args) {
        Snake s = new Snake(1, 1, 1);
        Grid startGrid = new Grid(Constants.SIZE, s, false);
        startGrid.printGrid();
        System.out.println("================ Starting grid ================");
        
        Node startNode = new Node(startGrid);
        startNode.setHeuristic();
        startNode.generateSuccessors();

        Node goalNode = new Node(startGrid.getGoalState());

        A_star(startNode, goalNode, 0);
        //*/

        /*while(true) {
            Scanner input = new Scanner(System.in);
            System.out.print("Input: ");
            char in = input.next().charAt(0);
            if(in == 'q' || !startGrid.moveSnake(in)) {
                System.out.println("Game over");
                break;
            }
            startGrid.isGoal();
            startGrid.printGrid();
        }
        */
    }

    public static void A_star(Node start, Node goal, int num) {
        /*boolean reached = false;
        ArrayList<Node> openList = new ArrayList<Node>();
        ArrayList<Node> closedList = new ArrayList<Node>();

        start.g = 0;
        start.f = start.g + start.h;

        openList.add(start);

        while (!reached) {
            Node current = openList.remove(getLowestf(openList, closedList));

            if(current.checkGoal()) {
                break;
            }
            else {
                closedList.add(current);

                ArrayList<Node> successors = current.generateSuccessors();
                
                for(int i=0; i < successors.size(); i++) {
                    Node neighbor = successors.get(i);

                }
            }
        }*/
        
        if(num == 20) {
            System.out.print(num);
            return;
        }
        
        ArrayList<Node> openList = new ArrayList<Node>();
        ArrayList<Node> closedList = new ArrayList<Node>();

        start.g = 0;
        start.f = start.g + start.h;

        openList.add(start);
        while(!openList.isEmpty()) {
            try {
                TimeUnit.MILLISECONDS.sleep(250);
            }catch(Exception e){
                e.printStackTrace();
            }

            //System.out.println("count:" + num);
            Node current = openList.get(getLowestf(openList,closedList));
            System.out.println("Score: " + score);

            current.print();
            //System.out.println("current.g: "+current.g);
            openList.remove(current);

            ArrayList<Node> successors = current.generateSuccessors();

            Iterator<Node> iter = successors.iterator();
            while(iter.hasNext()) {
                Node currSucc = iter.next();
                
                if (currSucc.checkGoal()) {
                    currSucc.print();
                    //System.out.println(openList.size());
                    //System.out.println("countf:" + num);
                    //System.out.println("=========================================");
                    /*if(num == 1) {
                        break outerloop;
                    }
                    num++;
                    
                    
                    openList.add(currSucc);*/
                    System.out.println("Goal reached");
                    currSucc.reset();
                    openList.clear();
                    closedList.clear();
                    score++;
                    A_star(currSucc, new Node(currSucc.data.getGoalState()), num+1);
                    break;
                }

                currSucc.setHeuristic();
                currSucc.f = currSucc.g + currSucc.h;

                if(!checkLowerInList(openList, currSucc)) {
                    if(!checkLowerInList(closedList, currSucc)) {
                        openList.add(currSucc);
                    }
                }
            }
            
            closedList.add(current);
        }

    }

    public static int getLowestf(ArrayList<Node> list, ArrayList<Node> closed) {
        

        int index = 0;
        double lowest = list.get(0).f;
        //System.out.println(lowest);
        if(!closed.isEmpty()) {

            Node prev = closed.get(closed.size()-1);
            //System.out.println("prev: " + prev.g);
            for(int i = 1; i < list.size(); i++) {
                if(list.get(i).f <= lowest && list.get(i).g == prev.g+1) {
                    lowest = list.get(i).f;
                    index = i;
                }
            }
        }
        else {
            for(int i = 1; i < list.size(); i++) {
                if(list.get(i).f <= lowest) {
                    lowest = list.get(i).f;
                    index = i;
                }
            }
        }
        
        //System.out.println("lowest: "+lowest);
        //System.out.println("lowest index: "+index);
        return index;
    }

    public static boolean checkLowerInList(ArrayList<Node> list, Node n) {
        if(list.isEmpty()) {
            return false;
        }

        boolean check = false;
        for(int i = 0; i < list.size(); i++) {
            if(n.h == list.get(i).h && list.get(i).f < n.f) {
                if(n.data.getSnake().getLocation().get(0)[0] == list.get(i).data.getSnake().getLocation().get(0)[0] && n.data.getSnake().getLocation().get(0)[1] == list.get(i).data.getSnake().getLocation().get(0)[1])
                    check = true;
            }
        }
        return check;
    }
}
