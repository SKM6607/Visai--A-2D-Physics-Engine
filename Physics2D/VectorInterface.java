package Physics2D;

interface VectorInterface {

    Vector2D add(Vector2D b);

    Vector2D subtract(Vector2D b);

    Vector2D multiply(float c);

    Vector2D divide(float c);

    Vector2D set(float x, float y);

    Vector2D normalize();

    String toString();

    float mag();

    Vector2D negateX();

    Vector2D negateY();

}