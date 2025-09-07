package Visual_Demo;

import Physics2D.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LineAnalyser extends JPanel {
    private final int HEIGHT, WIDTH, HANDLE_RADIUS,SPACING;
    private final BasicStroke LINE_STROKE = new BasicStroke(3);
    private final Vector2D p1, p2;
    private Vector2D inactivePivot;
    private Integer activeHandle = 0;
    private int length = 0;

    //----------------------------------------------------------------------------------------------------------------------
    public LineAnalyser(int LENGTH, int HANDLE_RADIUS, int WIDTH, int HEIGHT,int SPACING) {
        //SetBlock
        {
            this.HANDLE_RADIUS = HANDLE_RADIUS;
            this.WIDTH = WIDTH;
            this.HEIGHT = HEIGHT;
            p1 = new Vector2D((float) WIDTH / 2, (float) HEIGHT / 2);
            p2 = new Vector2D((float) WIDTH / 2 + LENGTH, (float) HEIGHT / 2);
            this.SPACING=SPACING;
        }
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
        MouseAdapter adapter = new MouseAdapter() {
            Integer isHandleSelected(Vector2D position) {
                if (p1.getDistance(position) <= HANDLE_RADIUS) {
                    return 1;
                }
                if (p2.getDistance(position) <= HANDLE_RADIUS) {
                    return 2;
                }
                return null;
            }

            Vector2D returnActiveHandle(int idx) {
                return idx == 1 ? p1 : p2;
            }

            Vector2D returnActivePivot(int idx) {

                return idx == 1 ? p2 : p1;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Vector2D mousePosition = new Vector2D(e.getX(), e.getY());
                activeHandle = isHandleSelected(mousePosition);
                if (activeHandle != null) {
                    inactivePivot = returnActivePivot(activeHandle);
                    length = (int) inactivePivot.getDistance(returnActiveHandle(activeHandle));
                }
            }

            @Override
            public void mouseReleased(MouseEvent mouse) {
                activeHandle = null;
                inactivePivot = null;
            }

            @Override
            public void mouseDragged(MouseEvent mouse) {
                // movement is just going to be x0 + r*cos(theta) & y0 + r*sin(theta) where theta is the dy/dx
                Vector2D mousePosition = new Vector2D(mouse.getX(), mouse.getY());
                if (activeHandle != null) {
                    inactivePivot = returnActivePivot(activeHandle);
                    int dy = (int) (mousePosition.getY() - inactivePivot.getY());
                    int dx = (int) (mousePosition.getX() - inactivePivot.getX());
                    float theta = (float) Math.atan2(dy, dx);
                    float xMovement = (float) (inactivePivot.getX() + length * Math.cos(theta));
                    float yMovement = (float) (inactivePivot.getY() + length * Math.sin(theta));
                    if (activeHandle == 1) {
                        p1.set(xMovement, yMovement);
                    } else {
                        p2.set(xMovement, yMovement);
                    }
                }
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent mouse) {
                setCursor(
                        (isHandleSelected(new Vector2D(mouse.getX(), mouse.getY())) != null) ?
                                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
                                : Cursor.getDefaultCursor()
                );
            }
        };
        addMouseListener(adapter);
        addMouseMotionListener(adapter);

    }

    public void drawHandle(Graphics2D gphcs, Vector2D position, boolean isActiveHandle) {
        int diameter = 2 * HANDLE_RADIUS;
        int xCenterOfHandle = (int) (position.getX() - HANDLE_RADIUS);
        int yCenterOfHandle = (int) (position.getY() - HANDLE_RADIUS);
        gphcs.setColor((isActiveHandle) ? Color.BLUE : Color.LIGHT_GRAY);
        gphcs.fillOval(xCenterOfHandle, yCenterOfHandle, diameter, diameter);
        gphcs.setStroke(new BasicStroke(1.5f));
        gphcs.setColor(Color.WHITE);
        gphcs.drawOval(xCenterOfHandle, yCenterOfHandle, diameter, diameter);
    }

    protected void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g2 = (Graphics2D) g1;
        for (int i = 0; i <= WIDTH ; i += SPACING) {
            g2.drawLine(i, 0, i, HEIGHT);
        }
        for (int i = 0; i <= HEIGHT ; i += SPACING) {
            g2.drawLine(0, i, WIDTH, i);
        }
        g2.setStroke(LINE_STROKE);
        g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, HEIGHT / 40));
        g2.setColor(Color.BLACK);
        g2.drawString(giveEquation(), 50, 50);
        g2.drawString(giveSlope(), 50, 80);
        g2.drawString(giveInclination(), 50, 110);
        g2.setColor(Color.DARK_GRAY);
        g2.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
        drawHandle(g2, p1, activeHandle != null && activeHandle == 1);
        drawHandle(g2, p2, activeHandle != null && activeHandle == 2);
    }

    private String giveEquation() {
        float dy = p2.getY() - p1.getY();
        float dx = p1.getX() - p2.getX();
        float result = p1.getX() * dy + p1.getY() * dx;
        return String.format("(%.2f)x + (%.2f)y = %.2f", dy, dx, result / 100f);
    }

    private String giveSlope() {
        float dy = p2.getY() - p1.getY();
        float dx = p2.getX() - p1.getX();
        if (dx <= 1e-5 && dx >= 0.0f) {
            return "Slope: Infinity";
        }
        return String.format("Slope: %.3f", dy / dx);
    }

    private String giveInclination() {
        float dy = p2.getY() - p1.getY();
        float dx = p2.getX() - p1.getX();
        float angle = (float) Math.atan2(dy, dx);
        return String.format("Angle: %.2f°", (180 / Math.PI) * -angle);
    }

}
