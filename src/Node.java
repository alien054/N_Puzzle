import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("Duplicates")
public class Node {
    private int [][] grid;
    private int [][] goal;
    HashMap<Integer, Pair<Integer,Integer>> goalMap = new HashMap<>();
    private int count = 0;
    private int BOARD_SIZE=4;
    public int g;
    public int f;
    public Node parent;

    //prev_move checking
    public char prev_move;

    //extra
    int parent_id;
    int id;

    Node(int[][] initialBoard , int[][] finalBoard){
        this.grid = new int[BOARD_SIZE][BOARD_SIZE] ;
        this.goal = new int[BOARD_SIZE][BOARD_SIZE] ;


        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                this.grid[i][j]=initialBoard[i][j];
            }
        }
        //this.grid = initialBoard;

        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                this.goal[i][j]=finalBoard[i][j];
                Pair<Integer,Integer> rowCol = new Pair<Integer, Integer>(i,j);
                goalMap.put(finalBoard[i][j],rowCol);
            }
        }

    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        if(parent == null){
            parent_id = -1;
            id = 0;
            prev_move = ' ';
        }
        this.parent = parent;
    }

    int getInvCount(int[] linearGrid){
        int invCount=0;
        for(int i=0;i<BOARD_SIZE*BOARD_SIZE-1;i++){
            for(int j=i+1;j<BOARD_SIZE*BOARD_SIZE;j++){
                if(linearGrid[j] != 0 && linearGrid[i] != 0 && linearGrid[i]>linearGrid[j]){
                    invCount++;
                }
            }
        }
        return invCount;
    }

    int findXPosition(int[][] puzzle){
        int xpos = -1;
        for(int i=BOARD_SIZE-1;i>=0;i--){
            for(int j=BOARD_SIZE-1;j>=0;j--){
                if(puzzle[i][j]==0){
                    xpos = BOARD_SIZE-i;
                }
            }
        }
        return xpos;
    }

    ArrayList<Node> getNewStates() {
        ArrayList<Node> newStates = new ArrayList<>();
        Node node;

//        int[][] tempGrid = new int[BOARD_SIZE][BOARD_SIZE];

//        for(int i=0;i<BOARD_SIZE;i++){
//            for(int j=0;j<BOARD_SIZE;j++){
//                tempGrid[i][j] = this.grid[i][j];
//            }
//        }
        int[][] tempGrid = this.grid;

        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                if(this.grid[i][j] == 0){
                    if(i-1>=0 && this.prev_move != 'u'){
                        tempGrid[i][j] = tempGrid[i-1][j];
                        tempGrid[i-1][j] = 0;

                        node = new Node(tempGrid,this.goal);
                        node.parent_id = this.id;
                        node.id = this.id + 2;
                        node.prev_move = 'd';

                        node.setG(this.getG()+1);
                        node.setF(node.getG()+node.totalManhattanDistance2());
                        node.setParent(this);

                        newStates.add(node);

                        //node.printBoard();

                        tempGrid[i-1][j] = tempGrid[i][j];
                        tempGrid[i][j]=0;
                    }

                    if(j-1>=0 && this.prev_move != 'l'){
                        tempGrid[i][j] = tempGrid[i][j-1];
                        tempGrid[i][j-1] = 0;

                        node = new Node(tempGrid,this.goal);
                        node.parent_id = this.id;
                        node.id = this.id + 4;
                        node.prev_move = 'r';

                        node.setG(this.getG()+1);
                        node.setF(node.getG()+node.totalManhattanDistance2());
                        node.setParent(this);

                        newStates.add(node);

                        //node.printBoard();

                        tempGrid[i][j-1] = tempGrid[i][j];
                        tempGrid[i][j]=0;
                    }

                    if(i+1<BOARD_SIZE && this.prev_move != 'd'){
                        tempGrid[i][j] = tempGrid[i+1][j];
                        tempGrid[i+1][j] = 0;

                        node = new Node(tempGrid,this.goal);
                        node.parent_id = this.id;
                        node.id = this.id + 1;
                        node.prev_move = 'u';

                        node.setG(this.getG()+1);
                        node.setF(node.getG()+node.totalManhattanDistance2());
                        node.setParent(this);

                        newStates.add(node);
                        //node.printBoard();

                        tempGrid[i+1][j] = tempGrid[i][j];
                        tempGrid[i][j]=0;
                    }

                    if(j+1<BOARD_SIZE && this.prev_move != 'r'){
                        tempGrid[i][j] = tempGrid[i][j+1];
                        tempGrid[i][j+1] = 0;

                        node = new Node(tempGrid,this.goal);
                        node.parent_id = this.id;
                        node.id = this.id + 3;
                        node.prev_move = 'l';

                        node.setG(this.getG()+1);
                        node.setF(node.getG()+node.totalManhattanDistance2());
                        node.setParent(this);

                        newStates.add(node);

                        //node.printBoard();

                        tempGrid[i][j+1] = tempGrid[i][j];
                        tempGrid[i][j]=0;
                    }
                }
            }
        }

        return  newStates;
    }

    boolean isSolveable(){
        int[] linearPuzzle = new int[16];
        int idx=0;

        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                linearPuzzle[idx] = this.grid[i][j];
                idx++;
            }
        }

        int invCount = getInvCount(linearPuzzle);
        int blankPos  = findXPosition(this.grid);

//        System.out.println("inv :"+invCount);
//        System.out.println("blnk: "+blankPos);

        if(blankPos%2==0){
            return invCount%2 != 0;
        }else {
            return invCount%2 == 0;
        }

    }

    boolean goalTest(){
        //prev

//        boolean isGoal;
//
//        for(int i=0;i<BOARD_SIZE;i++){
//            for(int j=0;j<BOARD_SIZE;j++){
//                if(this.grid[i][j] != this.goal[i][j]){
//                    return false;
//                }
//            }
//        }
//
//        return true;


        //new V1

//        return Arrays.deepHashCode(this.grid) == Arrays.deepHashCode(this.goal);

        //new V2
        return Arrays.deepToString(this.grid).hashCode() == Arrays.deepToString(this.goal).hashCode();


    }

    int totalDisplacement(){
        int missPlacedTile = 0;

        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                if(this.grid[i][j] != 0 && this.grid[i][j] != this.goal[i][j]){
//                    System.out.println("grid " + grid[i][j]);
//                    System.out.println("goal " + goal[i][j]);
                    missPlacedTile++;
                }
            }
        }

        return missPlacedTile;
    }

    int getmanhattanDistance(int tile,int grid_i,int grid_j){
        int distance = -1;

        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                if(this.goal[i][j] == tile){
                    distance = Math.abs(i-grid_i) + Math.abs(j-grid_j);
//                    System.out.println(tile + " " + distance);
                }
            }
        }

        return distance;
    }
    int totalManhattanDistance(){
        int mannDistance = 0;

        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                if(this.grid[i][j] != 0){
                    mannDistance += getmanhattanDistance(this.grid[i][j],i,j);
                }
            }
        }

        return mannDistance;
    }

    int totalManhattanDistance2(){
        int mannDistance = 0;
        int goal_i;
        int goal_j;

        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                if(this.grid[i][j] != 0){
                    Pair<Integer,Integer> goalRC = goalMap.get(grid[i][j]);
                    goal_i = goalRC.getFirst();
                    goal_j = goalRC.getSecond();
                    //System.out.println("tile: " + grid[i][j] + "row: " + goal_i + "col: " + goal_j);
                    mannDistance += (Math.abs(i-goal_i) + Math.abs(j-goal_j));
                }
            }
        }

        return mannDistance;
    }

    void printBoard(){
        System.out.println();
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                System.out.print(this.grid[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println("g: " + this.getG());
        System.out.println();
    }

    void printParent(){
        System.out.println("id: "+this.id+"parent: "+this.parent_id);
    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Node)) return false;
//        Node node = (Node) o;
//        return Arrays.equals(grid, node.grid);
//    }
//
//    @Override
//    public int hashCode() {
//        return Arrays.hashCode(grid);
//    }

    public  boolean isequal(Node check){
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                if(this.grid[i][j] != check.grid[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Node)) return false;

        Node check = (Node) obj;

        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                if(this.grid[i][j] != check.grid[i][j]){
                    return false;
                }
            }
        }
        return true;


        //new
//        boolean ret,ret1,ret2;
//        ret = Arrays.deepToString(this.grid).hashCode() == Arrays.deepToString(check.grid).hashCode();
//        ret1 = Arrays.deepHashCode(this.grid) == Arrays.deepHashCode(check.grid);
//        ret2 = Arrays.hashCode(this.grid) == Arrays.hashCode(check.grid);
//        if(ret && !ret2){
//            System.out.println("[");
//            this.printBoard();
//            check.printBoard();
//            System.out.println("]");
//        }
//        return Arrays.deepToString(this.grid).hashCode() == Arrays.deepToString(check.grid).hashCode();
    }

    @Override
    public int hashCode() {
        //prev
        //return Arrays.hashCode(grid);

        //new V1
//        return Arrays.deepHashCode(this.grid);

        //new V1
        return Arrays.deepToString(this.grid).hashCode();
    }

}
