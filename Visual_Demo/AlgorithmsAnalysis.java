package Visual_Demo;

import Physics2D.Point2D;

import javax.swing.*;
import java.awt.*;

public class AlgorithmsAnalysis extends JPanel {
    private final int fixedLength, radius;
    private final Point2D<Integer> pixel;

    public AlgorithmsAnalysis(int length, int radius, int WIDTH, int HEIGHT) {
        //setBlock
        {
            pixel = new Point2D<>(WIDTH / 3, HEIGHT / 5);
        }
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        fixedLength = length;
        this.radius = radius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        g1.setColor(Color.WHITE);
        Graphics2D g = (Graphics2D) g1;
        int startX = (int) pixel.getX();
        int startY = (int) pixel.getY();
        int endX = startX + fixedLength;
        int shiftY = 100;
        long iteration = (long) 1e3;
        displayTimeTaken("Normal Line Algorithm",
                analyseAlgorithm(() -> normalLineAlgorithm(g), iteration)
                , g,
                new Point2D<>(endX + 100, startY ));
        displayTimeTaken("DDA Line generating Algorithm",
                analyseAlgorithm(() -> digitalDifferentialAnalyserAlgorithm(g, startX, startY + shiftY, endX, startY + shiftY), iteration),
                g, new Point2D<>(endX, startY + shiftY));
        displayTimeTaken("Simple DDA",
                analyseAlgorithm(() -> simpleDDA(g, startX, startY + 2 * shiftY, endX, startY + 2 * shiftY), iteration),
                g, new Point2D<>(endX, startY + 2*shiftY));
        displayTimeTaken("Circle DDA",
                analyseAlgorithm(() -> circleDDA(g, startX, startY + 3 * shiftY, radius), iteration),
                g, new Point2D<>(endX, startY + 3*shiftY));
        displayTimeTaken("Parametrized Form of Circle",
                analyseAlgorithm(() -> parametrisedForm(g, startX, startY + 4 * shiftY, radius), iteration),
                g, new Point2D<>(endX, startY + 4*shiftY));
        displayTimeTaken("Line Drawing Algorithm",
                analyseAlgorithm(() -> drawBresenhamLinesDrawingAlgorithm(g, startX, startY + 5 * shiftY, endX, startY + 5 * shiftY), iteration),
                g, new Point2D<>(endX, startY + 5*shiftY));
        displayTimeTaken("Bresenham Circle Drawing Algorithm",
                analyseAlgorithm(() -> drawBresenhamCircleDrawingAlgorithm(g, startX, startY + 6 * shiftY, radius), iteration),
                g, new Point2D<>(endX,startY + 6*shiftY));
    }

    /**
     * It simulates a pixel, you can indeed do this with other methods such as drawRect(),Oval() etc.
     */
    private void simulatePixel(Graphics2D graphics2D, Point2D<Integer> pixelPosition) {
        graphics2D.drawLine((int) pixelPosition.getX(), (int) pixelPosition.getY(), (int) pixelPosition.getX(), (int) pixelPosition.getY());
    }

    private void normalLineAlgorithm(Graphics2D g) {
        for (int i = (int) pixel.getX(); i < (int) pixel.getX() + fixedLength; i++) {
            simulatePixel(g, new Point2D<>(i, pixel.getY()));
        }
    }

    /**
     * Symmetric DDA actually...
     */
    private void digitalDifferentialAnalyserAlgorithm(Graphics2D g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = dx / (float) steps;
        float yIncrement = dy / (float) steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            simulatePixel(g, new Point2D<>((int) x, (int) y));
            x += xIncrement;
            y += yIncrement;
        }
    }

    /**
     * It displays the time taken by the algorithm
     *
     * @param timeTaken
     */
    private void displayTimeTaken(String methodName, long timeTaken, Graphics2D g, Point2D<Integer> position) {
        g.drawString(String.format("Algorithm Name:%s, Time Taken: %d", methodName, timeTaken), position.getX(), position.getY());
    }

    /**
     * Simple DDA
     */
    private void simpleDDA(Graphics2D g, int p1, int q1, int p2, int q2) {
        float p = p1, q = q1;
        float m = (float) (q2 - q1) / (p2 - p1);
        float p_ = p, q_;
        int i = 0;
        while (p_ < p2) {
            p_ = p + 1;
            q_ = q + m * i;
            simulatePixel(g, new Point2D<>(p_, q_));
            p = p_;
            q = q_;
            i++;
        }
    }

    /**
     * Runs a program for n-iterations
     *
     * @param runnable
     * @param iterations
     * @return returns the time taken
     */
    private long analyseAlgorithm(Runnable runnable, long iterations) {
        long timeStart = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            runnable.run();
        }
        long timeEnd = System.currentTimeMillis();
        return timeEnd - timeStart;
    }

    private void parametrisedForm(Graphics2D graphics, int xShift, int yShift, int radius) {
        float theta;
        for (theta = 0; theta < 2 * Math.PI; theta += 0.01f) {
            simulatePixel(graphics, new Point2D<>((float) (xShift + radius * Math.cos(theta)), (float) (yShift + radius * Math.sin(theta))));
        }
    }

    private void circleDDA(Graphics2D g, int xShift, int yShift, int radius) {
        float theta = (1f / radius);
        float x = radius, y = 0;
        float nx, ny;
        while (x > y) {
            nx = x - y * theta;
            ny = y + theta * x;
            int[][] signs = {
                    {1, 1},
                    {-1, 1},
                    {1, -1},
                    {-1, -1}
            };
            for (int i = 0; i < 4; i++) {
                int sx = signs[i][0];
                int sy = signs[i][1];
                simulatePixel(g, new Point2D<>(sx * nx + xShift, sy * ny + yShift));
                simulatePixel(g, new Point2D<>(sx * ny + xShift, sy * nx + yShift));
            }
            x = nx;
            y = ny;
        }
    }

    private void drawBresenhamLinesDrawingAlgorithm(Graphics2D g, int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x1 > x0) ? 1 : -1;
        int sy = (y1 > y0) ? 1 : -1;
        int error = dx - dy;
        while (true) {
            simulatePixel(g, new Point2D<>(x0, y0));
            int e2 = error * 2;
            if (x0 == x1 && y0 == y1) break;
            if (e2 > -dy) {
                error -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                error += dx;
                y0 += sy;
            }
        }
    }

    private void drawBresenhamCircleDrawingAlgorithm(Graphics2D g, int x, int y, int radius) {
        int xc = 0;
        int yc = radius;
        int p = 1 - radius;
        int[][] allQuadrants = {
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1}
        };
        while (xc <= yc) {
            for (int[] quadrant : allQuadrants) {
                simulatePixel(g, new Point2D<>(x + quadrant[0] * xc, y + quadrant[1] * yc));
                simulatePixel(g, new Point2D<>(x + quadrant[1] * yc, y + quadrant[0] * xc));
            }

            xc++;
            if (p < 0) {
                p += 2 * xc + 1;
            } else {
                yc--;
                p += 2 * (xc - yc) + 1;
            }
        }
    }
}
