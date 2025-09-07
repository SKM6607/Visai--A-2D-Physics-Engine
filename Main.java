import Visual_Demo.*;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private static final int HEIGHT=1000,WIDTH=1800;
    private static final int RADIUS=30;
    private static final int LENGTH=200;
    Dimension size=new Dimension(WIDTH,HEIGHT);
    private static JButton[] buttons=new JButton[3];
    static {
        buttons[0] = new JButton("Ball Collision");
        buttons[1] = new JButton("Line Analyser");
        buttons[2] = new JButton("Algorithms Analysis");
        for (JButton button:buttons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
            button.setBorder(BorderFactory.createEtchedBorder());
        }
    }
    Main(String title){
        super(title);
        JPanel panel =new JPanel();
        JLabel titleOf=new JLabel("DEMO OF PHYSICS ENGINE");
        titleOf.setFont(new Font(Font.SERIF,Font.BOLD,40));
        titleOf.setHorizontalAlignment(SwingConstants.CENTER);
        titleOf.setOpaque(true);
        titleOf.setBackground(Color.BLACK);
        titleOf.setForeground(Color.WHITE);
        JPanel wrapper=new JPanel(new GridBagLayout());
        BoxLayout layout=new BoxLayout(panel,BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        panel.add(buttons[0]);
        panel.add(Box.createVerticalStrut(25));
        panel.add(buttons[1]);
        panel.add(Box.createVerticalStrut(25));
        panel.add(buttons[2]);
        buttonsActionListener();
        panel.setBackground(Color.black);
        setLayout(new BorderLayout());
        wrapper.add(panel);
        wrapper.setBackground(Color.black);
        wrapper.setPreferredSize(new Dimension(500,500));
        add(titleOf,BorderLayout.NORTH);
        add(wrapper,BorderLayout.CENTER);
        setSize(size);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    void buttonsActionListener(){
        buttons[0].addActionListener(_->{
            JFrame childFrame=new JFrame("Ball Collision");
            childFrame.add(new BallCollision(WIDTH,HEIGHT,15,25));
            childFrame.setSize(size);
            childFrame.setLocationRelativeTo(this);
            childFrame.setVisible(true);
        });
        buttons[1].addActionListener(_->{
            JFrame childFrame=new JFrame("Line Analyser");
            childFrame.add(new LineAnalyser(LENGTH,RADIUS-10,WIDTH,HEIGHT,25));
            childFrame.setSize(size);
            childFrame.setLocationRelativeTo(this);
            childFrame.setVisible(true);
        });
        buttons[2].addActionListener(_->{
            JFrame childFrame=new JFrame("Algorithms Analysis");
            childFrame.add(new AlgorithmsAnalysis(LENGTH,RADIUS,WIDTH,HEIGHT));
            childFrame.setSize(size);
            childFrame.setLocationRelativeTo(this);
            childFrame.setVisible(true);
        });
    }
    public static void main(String[] args) {
        new Main("Physics Engine Miniature");
    }
}
