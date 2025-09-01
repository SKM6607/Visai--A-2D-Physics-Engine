package Physics2D;

public class Force2D extends Vector2D {
    public Vector2D acceleration;
    public float mass;

    public Force2D() {
        this.x = this.y = 0;
    }

    public Force2D(float x, float y) {
        super(x, y);
        acceleration = Vector2D.ZERO_VECTOR;
        mass = 1;

    }

    public Force2D(Vector2D force, Vector2D acceleration, float mass) {
        super(force.x, force.y);
        this.acceleration = acceleration;
        this.mass = mass;
    }

    public void applyForce(Vector2D force) {
        this.acceleration.add(force.divide(mass));
    }

}
