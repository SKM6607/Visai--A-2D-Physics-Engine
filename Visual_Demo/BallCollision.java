package Visual_Demo;

import Physics2D.Vector2D;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;


public class BallCollision extends JPanel implements ActionListener {
    private final int screenWidth, screenHeight;
    private int ballsCount, gridSpacing;
    private final double FRICTION = 0.99;
    private Timer timer;
    private ArrayList<Ball> balls;
    private boolean isLocked = false;
    private final ArrayList<int[]> curvePoints=new ArrayList<>();
    public BallCollision(int WIDTH, int HEIGHT, int BALLS, int GRID_SPACING) {
        {
            this.screenWidth = WIDTH;
            this.screenHeight = HEIGHT;
            this.ballsCount = BALLS;
            this.gridSpacing = GRID_SPACING;
        }
        JPanel jPanel = getJPanel(BALLS);
        add(jPanel,BorderLayout.NORTH);
        //SetBlock
        balls = new ArrayList<>();
        startAnimation();

        MouseAdapter adapter = new MouseAdapter() {
            private Ball selectedBall = new Ball();

            public void glowOnHover(Ball ball, Vector2D position) {
                if (ball.getDistance(position) <= ball.radius) {
                    if (ball.color != Color.PINK) {
                        Color previous = ball.color;
                        ball.setColor(Color.PINK);
                        repaint();
                        if (ball.resetColorTimer != null && ball.resetColorTimer.isRunning()) {
                            ball.resetColorTimer.stop();
                        }
                        ball.resetColorTimer = new Timer(3000, _ -> {
                            ball.setColor(previous);
                        });
                        ball.resetColorTimer.setRepeats(false);
                        ball.resetColorTimer.start();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Vector2D vector = new Vector2D(e.getX(), e.getY());
                for (Ball ball : balls) {
                    if (ball.getDistance(vector) <= ball.radius) {
                        isLocked = true;
                        break;
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Vector2D vector = new Vector2D(e.getX(), e.getY());
                for (Ball ball : balls) {
                    boolean condition = ball.getDistance(vector) <= ball.radius;
                    if (condition) {
                        selectedBall = ball;
                    }
                }
                if (isLocked) {
                    selectedBall.set(vector);
                    selectedBall.velocity.set(Vector2D.ZERO_VECTOR);
                    selectedBall.gravity.set(Vector2D.ZERO_VECTOR);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isLocked) {
                    isLocked = false;
                    selectedBall.gravity.set(0, 0.99f);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                boolean hovering = false;
                Vector2D vector = new Vector2D(e.getX(), e.getY());
                for (Ball ball : balls) {
                    glowOnHover(ball, vector);
                    if (ball.getDistance(vector) <= ball.radius) {
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        hovering = true;
                        break;
                    }
                }
                if (!hovering) setCursor(Cursor.getDefaultCursor());
            }
        };
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        addMouseMotionListener(adapter);
        addMouseListener(adapter);
        timer = new Timer(16, this); // ~60 FPS
        timer.start();
    }

    private @NotNull JPanel getJPanel(int BALLS) {
        GridLayout layout=new GridLayout(1,2);
        JLabel jLabel=new JLabel(String.format("The number of balls: %d", BALLS));
        JSlider slider=new JSlider(0,50, BALLS);
        slider.setBackground(Color.BLACK);
        slider.addChangeListener(_->{
            this.ballsCount=slider.getValue();
            jLabel.setText(String.format("The number of balls: %d",ballsCount));
            startAnimation();
        });
        jLabel.setOpaque(true);
        jLabel.setBackground(Color.BLACK);
        jLabel.setForeground(Color.WHITE);
        JPanel jPanel=new JPanel(layout);
        jPanel.setPreferredSize(new Dimension(400,50));
        jPanel.add(jLabel);
        jPanel.add(slider);
        return jPanel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1f));
        for (int i = 0; i <= screenWidth; i += gridSpacing) {
            g2.drawLine(i, 0, i, screenHeight);
        }
        for (int i = 0; i <= screenHeight ; i += gridSpacing) {
            g2.drawLine(0, i, screenWidth, i);
        }
        g2.setStroke(new BasicStroke(5f));
        drawCurve(g2,screenWidth-500,screenHeight-100,100,1,g.getColor());
        for (Ball b : balls) {
            b.draw(g);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Ball b : balls) {
            b.update(screenWidth, screenHeight, FRICTION);
        }
        checkCollisions();
        repaint();
    }
    private void simulatePixel(Graphics2D g,int x,int y){
        g.drawLine(x,y,x,y);
    }
    private void drawCurve(Graphics2D g,int xShift,int yShift,int Amplitude,int widthFactor,Color color){
        float max= (float) (Math.PI/2);
        int x=0;
        g.setColor(Color.WHITE);
        for (float i = 0; i < max; i+=0.01f) {
            int sinVal=yShift+(int) (Amplitude*Math.sin(widthFactor*(i/0.5f)));
            simulatePixel(g,xShift+(x), sinVal);
            curvePoints.add(new int[]{xShift+x,sinVal});
            x++;
        }
        g.setColor(color);
    }
    // Handle collisions between balls
    private void checkCollisions() {
        for (Ball ball : balls) {

            for (Ball otherBall : balls) {
                if (ball != otherBall) {
                    Ball a = ball;
                    Ball b = otherBall;

                    double dist = a.getDistance(b);
                    double minDist = a.radius + b.radius;

                    if (dist < minDist) {
                        // Normalize
                        double nx = (a.getX() - b.getX()) / dist;
                        double ny = (a.getY() - b.getY()) / dist;
                        float velocity_A_X = a.velocity.getX();
                        float velocity_A_Y = a.velocity.getY();
                        float velocity_B_X = b.velocity.getX();
                        float velocity_B_Y = b.velocity.getY();
                        // Relative velocity
                        double vx = velocity_A_X - velocity_B_X;
                        double vy = velocity_A_Y - velocity_B_Y;
                        double dot = nx * vx + ny * vy;

                        if (dot > 0) continue; // already separating

                        // Elastic collision (1D along normal)
                        double m1 = a.mass;
                        double m2 = b.mass;
                        double coeff = (2 * dot) / (m1 + m2);

                        a.velocity.set((float) (velocity_A_X - coeff * m2 * nx), (float) (velocity_A_Y - coeff * m2 * ny));
                        b.velocity.set((float) (velocity_B_X + coeff * m1 * nx), (float) (velocity_B_Y + coeff * m1 * ny));

                        // Push balls apart to prevent overlap
                        double overlap = 0.5 * (minDist - dist + 1);
                        a.set((float) (a.getX() - overlap * nx), (float) (a.getY() - overlap * ny));
                        b.set((float) (b.getX() + overlap * nx), (float) (b.getY() + overlap * ny));
                    }
                }
            }
        }
    }
    void startAnimation(){
        balls.clear();
        Random random = new Random();
        for (int i = 0; i < ballsCount; i++) {
            int randomX = random.nextInt(0,screenWidth);
            int randomY = random.nextInt(0, screenHeight);
            int randomVecX = random.nextInt(25);
            int randomVecY = random.nextInt(25);
            int color = random.nextInt(0, 10);
            int radius = random.nextInt(5, 25);
            int mass = random.nextInt(1, 10);
            balls.add(new Ball(new Vector2D(randomX, randomY), new Vector2D(randomVecX, randomVecY), radius, mass, COLORS.retColors(color)));
        }
    }
    private enum COLORS {
        WHITE(0, Color.WHITE),
        RED(1, Color.RED),
        BLUE(2, Color.BLUE),
        GREEN(3, Color.GREEN),
        DARK_BLUE(4, new Color(0, 0, 139)),
        CYAN(5, Color.CYAN),
        GRAY(6, Color.GRAY),
        DARK_GREEN(7, new Color(0, 100, 0)),
        PINK(8, Color.PINK),
        BROWN(9, new Color(76, 0, 0));

        private int ID;
        private Color color;

        COLORS(int ID, Color color) {
            this.ID = ID;
            this.color = color;
        }

        public static Color retColors(int ID) {
            for (COLORS c : COLORS.values()) {
                if (c.ID == ID) return c.color;
            }
            return null; // or throw an exception if ID is invalid
        }
    }

    // Ball class
    static final class Ball extends Vector2D {
        final Vector2D velocity;
        final Vector2D gravity;
        private final int radius;
        private final double mass;
        public Timer resetColorTimer;
        private Color color;

        Ball() {
            this.velocity = ZERO_VECTOR;
            this.gravity = ZERO_VECTOR;
            this.radius = 0;
            this.mass = 0;
        }

        Ball(Vector2D position, Vector2D velocity, int radius, int mass, Color color) {
            super(position);
            this.velocity = velocity;
            this.radius = radius;
            this.mass = mass;
            this.color = color;
            this.gravity = new Vector2D(0, 0.99f);
        }
        void setColor(Color color) {
            this.color = color;
        }

        void update(int width, int height, double friction) {
            // Apply friction
            velocity.set((float) (velocity.getX() * friction), (float) (velocity.getY() * friction)).add(gravity);
            // Move
            x += velocity.getX();
            y += velocity.getY();

            // Bounce with walls
            if (x - radius < 0) {
                x = radius;
                velocity.negateX();
            }
            if (x + radius > width) {
                x = width - radius;
                velocity.negateX();
            }
            if (y - radius < 0) {
                y = radius;
                velocity.negateY();
            }
            if (y + radius > height) {
                y = height - radius;
                velocity.negateY();
            }
        }
        void draw(Graphics g) {
            g.setColor(color);
            g.fillOval((int) (x - radius), (int) (y - radius), radius * 2, radius * 2);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            JFrame jFrame=new JFrame("Ball Collision");
            jFrame.add(new BallCollision(1500,800,10,25));
            jFrame.setSize(1500,800);
            jFrame.setVisible(true);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
