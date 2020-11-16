import java.util.Arrays;

public class HashTest {
    public static void main(String[] args) {
        int [][] box1 = new int[2][2];
        int [][] box2 = new int[2][2];
        int [][] box3 = new int[2][2];

        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                //box1 and box3 is same
                box1[i][j]=i*j+1;
                box3[i][j]=i*j+1;

                box2[i][j]=i*j+10;
            }
        }

        TestObj obj1 = new TestObj(box1);
        TestObj obj2 = new TestObj(box2);
        TestObj obj3 = new TestObj(box3);

        System.out.println(Arrays.deepHashCode(obj1.box));
        System.out.println(Arrays.deepHashCode(obj2.box));
        System.out.println(Arrays.deepHashCode(obj3.box));

    }
}

