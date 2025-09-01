package Physics2D;

interface CartesianCoordinatesInterface<T extends Number> {
    float getX();

    float getY();

    void setLocation(T x, T y);

    <K extends Point2D> void setLocation(K point);

    void move(T x, T y);

    <K extends Point2D> void move(K point);

    void translate(T dx, T dy);

    <K extends Point2D> void translate(K dp);

    float[] getLocation();

    <K extends Point2D> K getLocationPoint();
}