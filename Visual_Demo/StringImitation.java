package Visual_Demo;

import Physics2D.Force2D;
import Physics2D.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StringImitation extends JPanel {
    private final int STRING_LENGTH;
    private final ArrayList<Particle> particles = new ArrayList<>();

    public StringImitation(int length, int startX, int startY) {
        if (length <= 0) throw new IllegalArgumentException("Error: Length cannot be lesser than zero");
        STRING_LENGTH = length;
        for (int i = 0; i < STRING_LENGTH; i++) {
            particles.add(new Particle(new Vector2D(startX, startY + i), new Vector2D(), new Force2D(), 10));
        }
    }

    private static class Particle extends Vector2D {
        public Vector2D velocity;
        public Force2D force;
        public int mass;

        public Particle(Vector2D position, Vector2D velocity, Force2D force2D, int mass) {
            this.x = position.getX();
            this.y = position.getY();
            this.velocity = velocity;
            this.force = force2D;
            this.mass = mass;
        }

        public static void simulateParticle(Graphics2D g, Particle p) {
            g.drawLine((int) p.x, (int) p.y, (int) (p.x), (int) (p.y));
        }
    }

    private record Spring(Particle a, Particle b, double rest_length, double stiffness, double damping) {
        public Spring(Particle a, Particle b, double k, double damping) {
            this(
                    a,
                    b,
                    a.subtract(b).mag(),
                    k,
                    damping
            );
        }
    }

}
