//TODO: DEI MUDI DA
/*class BallMotion extends JPanel {
    private static final int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int START_X = 50, END_X = width - 50, START_Y = 100, END_Y = height - 200;

    private static class Ball {
        public final Vector2D position, velocity;
        public final static Vector2D gravity;
        public static final Force2D forceOffCorners;
        public float bounceFactor = -0.8f;
        public final float mass;
        public final float radius;
        public static final Vector2D friction;

        static {
            forceOffCorners = new Force2D(0.15f, 0.15f);
            gravity = new Vector2D(0, 0.99f);
            friction = new Vector2D(0.98f, 0f);
        }

        Ball(float xPos, float yPos, float xVelocity, float yVelocity, float mass, float radius) {
            position = new Vector2D(xPos, yPos);
            velocity = new Vector2D(xVelocity, yVelocity);
            if (mass == 0) mass++;
            this.mass = mass;
            this.bounceFactor = (this.bounceFactor * 20) / this.mass;
            this.radius = radius;
        }
    }

    private final ArrayList<Ball> balls = new ArrayList<>();

    public BallMotion(int numberOfBalls, int maxRadius) {
        Random random = new Random();
        for (int i = 0, mass = 10; i < numberOfBalls; i++, mass--) {
            if (mass == 1) mass = 10;
            float x = random.nextFloat(END_X - START_X - 2) + START_X;
            float radiusRange = random.nextFloat(maxRadius) + (float) maxRadius / 10;
            float y = random.nextFloat(END_Y - START_Y) + START_Y;
            float xVelocity = random.nextFloat(50) + 25;
            float yVelocity = random.nextFloat(25) + 25;
            this.balls.add(new Ball(x, y, xVelocity, yVelocity, mass, Math.abs(radiusRange)));

        }
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        for (Ball ball : balls) {
            g.fillOval((int) (ball.position.x - ball.radius), (int) (ball.position.y - ball.radius), 2 * (int) ball.radius, 2 * (int) ball.radius);
        }
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(START_X, START_Y, END_X, START_Y);
        g.drawLine(START_X, START_Y, START_X, END_Y);
        g.drawLine(END_X, START_Y, END_X, END_Y);
        g.drawLine(START_X, END_Y, END_X, END_Y);
    }

    public void ballMotion() {
        Vector2D friction = Ball.friction;
        for (Ball ball : balls) {
            // update position
            ball.position.add(ball.velocity);

            // air resistance
            ball.velocity.multiply(0.99f);

            // snap tiny velocities
            if (Math.abs(ball.velocity.x) < 0.1f) ball.velocity.x = 0;
            if (Math.abs(ball.velocity.y) < 0.1f) ball.velocity.y = 0;

            // right wall
            if (ball.position.x + ball.radius >= END_X) {
                ball.position.x = END_X - ball.radius;
                ball.velocity.x *= ball.bounceFactor;
            }

            // left wall
            if (ball.position.x - ball.radius <= START_X) {
                ball.position.x = START_X + ball.radius;
                ball.velocity.x *= ball.bounceFactor;
            }

            // ceiling
            if (ball.position.y - ball.radius <= START_Y) {
                ball.position.y = START_Y + ball.radius;
                ball.velocity.y *= -ball.bounceFactor;
            }

            // floor
            if (ball.position.y + ball.radius >= END_Y) {
                ball.position.y = END_Y - ball.radius;
                ball.velocity.y *= -ball.bounceFactor;
                ball.velocity.x *= friction.x;
                if (Math.abs(ball.velocity.y) < 0.1f) {
                    ball.velocity.y = 0;
                }
            }

            if (!(ball.position.y + ball.radius >= END_Y && Math.abs(ball.velocity.y) < 0.1f)) {
                ball.velocity.add(Ball.gravity);
            }
        }
        repaint();
    }


}*/
public class copy {
}
