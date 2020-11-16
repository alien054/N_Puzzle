import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) {
        int numberOfLine;
        int tile;
        int BOARD_SIZE=4;

        int [][] goal = new int[BOARD_SIZE][BOARD_SIZE];
        int [][] puzzle = new int[BOARD_SIZE][BOARD_SIZE];

        ArrayList<Node> puzzleList = new ArrayList<Node>();
        Node newPuzzle;


        try {
            File file = new File("input.txt");
            Scanner sc = new Scanner(file);

            numberOfLine = sc.nextInt();
            //System.out.println(numberOfLine);

            for(int i=0;i<BOARD_SIZE;i++){
                for(int j=0;j<BOARD_SIZE;j++){
                    //tile = Integer.parseInt(input.nextLine());
                    tile = sc.nextInt();
                    goal[i][j]= tile;
                }
            }

            for(int i=0;i<numberOfLine-1;i++){
                for(int j=0;j<BOARD_SIZE;j++){
                    for(int k=0;k<BOARD_SIZE;k++){
                        tile = sc.nextInt();
                        puzzle[j][k]= tile;
                    }
                }

                newPuzzle = new Node(puzzle,goal);

                puzzleList.add(newPuzzle);
            }

            for (Node node : puzzleList) {
                System.out.println(node.isSolveable());

                node.printBoard();

                AStar aStar = new AStar(node);

                if(node.isSolveable()){
                   ArrayList<Node>path = new ArrayList<>();

//                   Displacement
//
//                    System.out.println("Finding Path using Displacement");
//                    long startTime = System.nanoTime();
//                    Node end = aStar.displacementAStar();
//                    long endTime = System.nanoTime();
//                    long durationInNano = (endTime - startTime);
//                    long durationInMillis = TimeUnit.NANOSECONDS.toMillis(durationInNano);
//                    System.out.println("Total Time: : " + durationInMillis + "ms");
//
//
//
//                    System.out.println("Path Starts (displacement) ");
//                    while (end != null){
//                        path.add(end);
//                        end = end.getParent();
//                    }
//                    Collections.reverse(path);
//
//                    for (Node value : path) {
//                        value.printBoard();
//                    }
//                    System.out.println("Path Finished (displacement) ");


//                    Manhattan

                    long startTime2 = System.nanoTime();
                    System.out.println("Finding Path using Manhattan");
                    Node end2 = aStar.manhattanAStar();
                    long endTime2 = System.nanoTime();
                    long durationInNano2 = (endTime2 - startTime2);
                    long durationInMillis2 = TimeUnit.NANOSECONDS.toMillis(durationInNano2);
                    System.out.println("Total Time: : " + durationInMillis2 + "ms");


                    path.clear();



                    System.out.println("Path Starts (manhattan) ");
                    while (end2 != null){
                        path.add(end2);
                        end2 = end2.getParent();
                    }
                    Collections.reverse(path);

                    for (Node value : path) {
                        value.printBoard();
                    }
                    System.out.println("Path Finished (manhattan) ");
                } else{
                    System.out.println("Not solvable");
                }

//                ArrayList<Node> moves = node.getNewStates();
//                for (Node move : moves) {
//                    move.printBoard();
//                }

//                System.out.println("solve: "+node.isSolveable());
//                System.out.println("isGoal: "+node.goalTest());
//                System.out.println("disp: "+node.totalDisplacement());
//                System.out.println("manh: "+node.totalManhattanDistance());
            }

//                int[][] mypuzzle = new int[4][4];
//                for(int i=0;i<BOARD_SIZE;i++){
//                    for(int j=0;j<BOARD_SIZE;j++){
//                        mypuzzle[i][j]=i+j;
//                    }
//                }
//
//                Node mynode = new Node(mypuzzle,goal);
//                puzzleList.add(mynode);
//
//                int[][] mypuzzle2 = new int[4][4];
//                for(int i=0;i<BOARD_SIZE;i++){
//                    for(int j=0;j<BOARD_SIZE;j++){
//                        mypuzzle2[i][j]=i+j;
//                    }
//                }
//
//                Node mynode2 = new Node(mypuzzle2,goal);
//
//                System.out.println(puzzleList.contains(mynode2));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
