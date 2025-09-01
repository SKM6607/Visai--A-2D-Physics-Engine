package Physics2D;

public class AntiAligningBoundingBoxAABB {
    public final Vector2D min, max;

    public AntiAligningBoundingBoxAABB(Vector2D min, Vector2D max) {
        this.min = min;
        this.max = max;
    }

    public AntiAligningBoundingBoxAABB(int minX, int minY, int maxX, int maxY) {
        this.min = new Vector2D(minX, minY);
        this.max = new Vector2D(maxX, maxY);

    }

    public static boolean isColliding(AntiAligningBoundingBoxAABB A, AntiAligningBoundingBoxAABB B) {
        return (A.min.x < B.max.x && A.max.x > B.min.x) && (A.min.y < B.max.y && A.max.y > B.min.y);
    }
}
