//package Physics2D;
//
//public class FlatMath {
//    public static float length(Vector2D v) {
//        return (float) Math.sqrt(v.x * v.x + v.y * v.y);
//    }
//
//    public static float distance(Vector2D v1, Vector2D v2) {
//        return (float) Math.sqrt((v2.x - v1.x) * (v2.x - v1.x) + (v2.y - v1.y) * (v2.y - v1.y));
//    }
//
//    public static Vector2D normalize(Vector2D v) {
//        int magnitude = FlatMath.length(v);
//        return new Vector2D(v.x / magnitude, v.y / magnitude);
//    }
//
//    public static float dotProduct(Vector2D v1, Vector2D v2) {
//        return (v1.x * v2.x + v1.y * v2.y);
//    }
//
//    public static float crossProduct(Vector2D v1, Vector2D v2) {
//        return (v1.x * v2.y - v1.y * v2.x);
//    }
//
//    public static float clamp(float min, float max, float value) {
//        if (min > max) throw new IndexOutOfBoundsException("Minimum is greater than Maximum");
//        if (value > max) return max;
//        if (value < min) return min;
//        if (min == max) return min;
//        return value;
//    }
//    public static int clamp(int min, int max, int value) {
//        if (min > max) throw new IndexOutOfBoundsException("Minimum is greater than Maximum");
//        if (value > max) return max;
//        if (value < min) return min;
//        if (min == max) return min;
//        return value;
//    }
//}
