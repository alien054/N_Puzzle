import java.util.Comparator;

public class fCompare implements Comparator<Node> {
//    @Override
//    public int compare(Node o1, Node o2) {
//        if(o1.f < o2.f){
//            return -1;
//        }
//        else if(o1.f > o2.f){
//            return 1;
//        }
//        else {
//            return 0;
//        }
//    }

    @Override
    public int compare(Node o1, Node o2) {
        if(o1.f <= o2.f){
            return -1;
        }
        else{
            return 1;
        }
    }
}
