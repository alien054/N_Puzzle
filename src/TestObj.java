public class TestObj {
    int[][] box;

    TestObj(int [][] inputBox){
        this.box = new int[2][2];
        box = inputBox;
    }

    public void print(){
        System.out.println("Printing: ");
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                System.out.print(box[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
