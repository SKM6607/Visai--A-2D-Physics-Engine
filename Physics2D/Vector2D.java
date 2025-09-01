package Physics2D;

import java.util.Objects;

public class Vector2D implements VectorInterface {
    public static final Vector2D ZERO_VECTOR;

    static {
        ZERO_VECTOR = new Vector2D(0f, 0f);
    }

    protected float x, y;

    public Vector2D(){}

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D other) {
        this.x = other.x;
        this.y = other.y;
    }

    /**
     * Static operations (non-mutating)
     */
    public static Vector2D add(Vector2D a, Vector2D b) {
        return new Vector2D(a.x + b.x, a.y + b.y);
    }

    public static Vector2D subtract(Vector2D a, Vector2D b) {
        return new Vector2D(a.x - b.x, a.y - b.y);
    }

    public static Vector2D multiply(Vector2D a, float c) {
        return new Vector2D(a.x * c, a.y * c);
    }

    public static Vector2D divide(Vector2D a, float c) {
        if (c == 0) throw new ArithmeticException("Error: Cannot divide by zero");
        return new Vector2D(a.x / c, a.y / c);
    }

    public static Vector2D negate(Vector2D a) {
        return new Vector2D(-a.x, -a.y);
    }

    public static Vector2D negateX(Vector2D a) {
        return new Vector2D(-a.x, a.y);
    }

    public static Vector2D negateY(Vector2D a) {
        return new Vector2D(a.x, -a.y);
    }

    /**
     * Random vector between (0,0) and (maxValue,maxValue)
     */
    public static Vector2D randomVector(float maxValue) {
        return new Vector2D((float) (Math.random() * maxValue),
                (float) (Math.random() * maxValue));
    }

    /**
     * Compare x component
     * @param otherX comparison value
     */
    public int compareX(float otherX) {
        if (x == otherX) return 0;
        return (x < otherX) ? -1 : 1;
    }

    /**
     * Compare y component
     */
    public int compareY(float otherY) {
        if (y == otherY) return 0;
        return (y < otherY) ? -1 : 1;
    }

    /**
     * Compare vector magnitude
     */
    public int compareVector(Vector2D other) {
        float m1 = this.mag();
        float m2 = other.mag();
        if (m1 == m2) return 0;
        return (m1 < m2) ? -1 : 1;
    }

    /**
     * Instance operations (mutating)
     */
    public Vector2D add(Vector2D b) {
        this.x += b.x;
        this.y += b.y;
        return this;
    }

    public Vector2D subtract(Vector2D b) {
        this.x -= b.x;
        this.y -= b.y;
        return this;
    }

    public Vector2D set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2D set(Vector2D other) {
        this.x = other.x;
        this.y = other.y;
        return this;
    }

    public Vector2D multiply(float c) {
        this.x *= c;
        this.y *= c;
        return this;
    }

    public Vector2D divide(float c) {
        if (c == 0) throw new ArithmeticException("Error: Cannot divide by zero");
        this.x /= c;
        this.y /= c;
        return this;
    }

    /**
     * Negates the overall vector
     */
    public Vector2D negate() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    /**
     * Negates the X component
     */
    public Vector2D negateX() {
        this.x = -this.x;
        return this;
    }

    /**
     * Negates the Y component
     */
    public Vector2D negateY() {
        this.y = -this.y;
        return this;
    }

    /**
     * Magnitude (length)
     */
    public float mag() {
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * Normalize to unit vector
     */
    public Vector2D normalize() {
        float magnitude = this.mag();
        if (magnitude != 0) {
            this.divide(magnitude);
        }
        return this;
    }

    /**
     * Equality check (value-based)
     */
    public boolean isEqual(Vector2D other) {
        return this.x == other.x && this.y == other.y;
    }

    /**
     * Proper equals() override
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector2D)) return false;
        Vector2D other = (Vector2D) obj;
        return Float.compare(x, other.x) == 0 &&
                Float.compare(y, other.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * String representation
     */
    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", x, y);
    }

    public void limit(float max) {
        float magnitude = mag();
        if (magnitude > max) {
            x = (x / magnitude) * max;
            y = (y / magnitude) * max;
        }
    }

    public Vector2D multiplyX(Vector2D other) {
        this.x = this.x * other.x;
        return this;
    }

    public Vector2D multiplyY(Vector2D other) {
        this.y = this.y * other.y;
        return this;
    }

    public Vector2D get() {
        return new Vector2D(x, y);
    }

    public float dotProduct(Vector2D vector) {
        return this.x * vector.x + this.y * vector.y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getDistance(Vector2D other) {
        float dx = other.x - this.x;
        float dy = other.y - this.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public static float dotProduct(Vector2D a,Vector2D b) {
        return b.x * a.x + a.y * b.y;
    }

    public void setX(float x){
        this.x=x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
