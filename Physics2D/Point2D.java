package Physics2D;

public class Point2D<T extends Number> implements CartesianCoordinatesInterface<T> {
    float x, y;

    public Point2D() {
        this(0, 0);
    }

    public Point2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point2D(Point2D other) {
        this.x = other.x;
        this.y = other.y;
    }
    public void setX(T x){
        this.x=x.floatValue();
    }
    public void setY(T y) {
        this.y = y.floatValue();
    }

    /**
     * Returns the horizontal x position of the source component.
     * @return
     * x an integer indicating horizontal position
     */
    @Override
    public float getX() {
        return x;
    }

    /**
     * Returns the vertical y position component.
     * @return
     * y an integer indicating vertical position
     */
    @Override
    public float getY() {
        return y;
    }


    @Override
    public void setLocation(T x, T y) {
        this.x = x.floatValue();
        this.y = y.floatValue();
    }

    @Override
    public <K extends Point2D> void setLocation(K point) {
        this.x = point.x;
        this.y = point.y;
    }

    @Override
    public void move(T x, T y) {
        this.x = x.floatValue();
        this.y = y.floatValue();
    }

    @Override
    public <K extends Point2D> void move(K point) {
        this.x = point.x;
        this.y = point.y;
    }

    @Override
    public void translate(T dx, T dy) {
        this.x += dx.floatValue();
        this.y += dy.floatValue();
    }

    public <K extends Point2D> void translate(K dp) {
        this.x += dp.x;
        this.y += dp.y;
    }
    @Override
    public float[] getLocation() {
        return new float[]{x, y};
    }

    @Override
    public Point2D getLocationPoint() {
        return this;
    }

    public float getDistance(Point2D other) {
        float dx = this.x - other.x;
        float dy = this.y - other.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }
}
