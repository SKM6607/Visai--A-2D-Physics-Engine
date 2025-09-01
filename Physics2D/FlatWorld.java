//package Physics2D;
//import java.util.ArrayList;
//public final class FlatWorld {
//    public enum bodyVariables {
//        MIN_BODY_SIZE(0.01f * 0.01f),
//        MAX_BODY_SIZE(64f * 64f),
//        MIN_DENSITY(0.5f),
//        MAX_DENSITY(21.4f);
//        public final float value;
//
//        bodyVariables(float value) {
//            this.value = value;
//        }
//    }
//
//    public static final int MIN_ITERATIONS = 1, MAX_ITERATIONS = 128;
//    private Vector2D gravityVector;
//    private ArrayList<FlatBody> bodyList;
//
//    public int getBodiesCount() {
//        return this.bodyList.size();
//    }
//
//    public FlatWorld() {
//        this.gravityVector = new Vector2D(0f, -9.81f);
//        this.bodyList = new ArrayList<>();
//    }
//
//    public FlatWorld(FlatBody flatBody) {
//        this.gravityVector = new Vector2D(0f, -9.81f);
//        this.bodyList = new ArrayList<>();
//        this.bodyList.add(flatBody);
//    }
//
//    public void addBody(FlatBody flatBody) {
//        this.bodyList.add(flatBody);
//    }
//
//    public void removeBody(FlatBody flatBody){
//        this.bodyList.remove(flatBody);
//    }
//
//    public FlatBody getBody(int index){
//        if(this.bodyList.size()<index||index<0){
//            throw new IndexOutOfBoundsException("Invalid Index");
//        }
//        return this.bodyList.get(index);
//    }
//
//
//}