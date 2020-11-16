import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

@SuppressWarnings("Duplicates")
public class AStar {
    PriorityQueue<Node> openSet;
    ArrayList<Node> closedSet;
    //HashMap<Node,Integer>closedSet;
//    HashSet<Node> closedSet;
    Node startNode;
    int exploredNodes;
    int expandedNodes;


    AStar(Node startNode){
        openSet = new PriorityQueue<Node>(new fCompare());
//        closedSet = new HashSet<>();
        //closedSet = new HashMap<Node, Integer>();
        closedSet = new ArrayList<>();
        exploredNodes = 0;
        expandedNodes = 0;
        this.startNode = startNode;
    }

    Node displacementAStar(){
        Node nextMinNode;
        closedSet.clear();
        openSet.clear();

        startNode.setParent(null);
        startNode.setG(0);
        startNode.setF(startNode.getG()+startNode.totalDisplacement());

        openSet.add(startNode);

        while(!openSet.isEmpty()){
            nextMinNode = openSet.poll();


//            System.out.println("{{{");
//            nextMinNode.printBoard();
//            System.out.println(nextMinNode.getF());
//            System.out.println("}}}");

            if(nextMinNode.goalTest()){
                System.out.println("Using Displacement");
                System.out.println("Total Nodes Explored: " + closedSet.size());
                System.out.println("Total Nodes Expanded: " + expandedNodes);
                System.out.println("Cost: " + nextMinNode.getF());
                return nextMinNode;
            }

            closedSet.add(nextMinNode);
            //closedSet.put(nextMinNode,nextMinNode.getF());

            ArrayList<Node> move = nextMinNode.getNewStates();

            for (Node node : move) {
                //if(!closedSet.containsKey(node)){
                if(!closedSet.contains(node)){
//                    node.printBoard();
//                    System.out.println(node.getF());
//                   System.out.println("---------");
                    if(!openSet.contains(node)){
                        node.setG(nextMinNode.getG()+1);
                        node.setF(node.getG()+node.totalDisplacement());
                        node.setParent(nextMinNode);
                        openSet.add(node);
                        expandedNodes++;
                    }
                    /*else {
                        //System.out.println();
                        int a= 0;
                        PriorityQueue<Node> cloneOpenSet = new PriorityQueue<>(openSet);
                        while (!cloneOpenSet.isEmpty()){
                            Node test = cloneOpenSet.poll();
                            if(test.equals(node)){
                                System.out.println(test.getF());
                                System.out.println(node.getG()+1+node.totalManhattanDistance());
                            }
                        }
                    }*/
                }
            }

        }
        return null;
    }

    Node manhattanAStar(){
        Node nextMinNode;
        closedSet.clear();
        openSet.clear();

        startNode.setParent(null);
        startNode.setG(0);
        startNode.setF(startNode.getG()+startNode.totalManhattanDistance2());

        openSet.add(startNode);

        while(!openSet.isEmpty()){
            nextMinNode = openSet.poll();

//            nextMinNode.printParent();//exx
//            System.out.println("g: " + nextMinNode.getG());
//            nextMinNode.printBoard();

            if(nextMinNode.goalTest()){
                System.out.println("Using  Manhattan");
                System.out.println("Total Nodes Explored: " + closedSet.size());
                System.out.println("Total Nodes Expanded: " + expandedNodes);
                System.out.println("Cost: " + nextMinNode.getF());
                return nextMinNode;
            }

            closedSet.add(nextMinNode);
            //closedSet.put(nextMinNode,nextMinNode.getF());

            ArrayList<Node> move = nextMinNode.getNewStates();

            for (Node node : move) {
                //if(!closedSet.containsKey(node)){
                if(!closedSet.contains(node)){

                    if(!openSet.contains(node)){
//                        node.setG(nextMinNode.getG()+1);
//                        node.setF(node.getG()+node.totalManhattanDistance2());
//                        node.setParent(nextMinNode);
                        openSet.add(node);
                        expandedNodes++;

                    }
                    else {
                        //System.out.println();
                        int a= 0;
                        //System.out.println("open");
                        PriorityQueue<Node> cloneOpenSet = new PriorityQueue<>(openSet);

                        while (!cloneOpenSet.isEmpty()){
                            Node test = cloneOpenSet.poll();
                            if(test.equals(node)){
                                int oldValue = test.getF();
                                //int newValue = nextMinNode.getG()+1+node.totalManhattanDistance();
                                int newValue = node.getF();

                                //System.out.println("old: " + oldValue + "new: " + newValue);
                                if(newValue < oldValue){
//                                    System.out.println(openSet.size());
//                                    System.out.println(openSet.remove(test));
//                                    System.out.println(openSet.size());
//                                    System.out.println("old: " + oldValue + "new: " + newValue);
                                    openSet.remove(test);
                                    openSet.add(node);
                                    break;
                                }
                            }
                        }
                    }
                }
//                else {
//                    int a= 0;
//                    //System.out.println("closed");
//                    int idx = closedSet.indexOf(node);
//                    Node test = closedSet.get(idx);
//
//                    if(node.getF() < test.getF()){
//                        openSet.remove(test);
//                        openSet.add(node);
//                    }
//                }

            }

        }
        return null;
    }

//    void test(){
//        ArrayList<Node> move = new ArrayList<>();
//
//        move = openSet.poll().getNewStates();
//
//        for (Node node : move) {
//            node.setG(0);
//            node.setF(node.getG()+node.totalDisplacement());
//            node.printBoard();
//            System.out.println(node.getF());
//            System.out.println("---------");
//            openSet.add(node);
//        }
//
//        Node node = openSet.poll();
//        node.printBoard();
//        System.out.println(node.getF());
//
//    }

}
