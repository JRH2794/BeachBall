import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/** @author Jennifer Holder
 *
 * NOTE FROM THE AUTHOR: The assignment wasn't completely clear on what it meant by 'rotating
 * the colors'.
 *
 * At first I thought that meant I should just have the colors move in order and without
 * the borders of the quadrants moving, just the colors. I decided that if the problem wanted that,
 * it would have said 'alternating the colors around the circle' or 'changing the colors of the quadrants'.
 * Since it specifically said "rotate" I did it like this with the quadrants moving a unit at a time.
 *
 * - Jennifer Holder
 * I also forgot to add @author to my last homework.
 *
 */
/** @purpose This program will use GUI to animate a four-colored beach ball
 * rotating in place by rotating the four colors. The beach ball
 * will be a solid circle with four different colors in each four quadrants.
 *
 * The program should use a Start button to begin the animation,
 * and a Stop button to pause it. Pressing Start replays it until
 * Stop is pressed again, and so forth.
 *
 *
 */

public class BeachBall extends JFrame implements ActionListener {

    // Declare global variables:
    public static final int WINDOW_WIDTH = 450;
    public static final int WINDOW_HEIGHT = 550;
    public static final int BALL_DIAMETER = 200;
    public static final int X_BALL = 100;
    public static final int Y_BALL = 100;

    boolean spinning;
    int blueStart = 0;
    int blueEnd = 90;
    int yellowStart = 90;
    int yellowEnd = 90;
    int redStart = 180;
    int redEnd = 90;

    // Declare global timer so there's only one of them:
    final Timer timer = new Timer(15, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent f) {

            if (spinning == true) {

                blueStart += 1;
                yellowStart += 1;
                redStart += 1;

                // Do the ends need to move?

                repaint(); // Send the new x's and y's.

            }
        }
    });

    /** @main
     * Create main method:
     * */
    public static void main(String[] args) {

        BeachBall gui = new BeachBall();
        gui.setVisible(true);

    }

    // Create beach ball and environment:
    public BeachBall() {

        super("Welcome to the Beach");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Basic int font style to add to the more complex one later:
        int style = Font.BOLD;

        // Create border for ball panel:
        Border border = BorderFactory.createEtchedBorder(Color.cyan, Color.cyan);
        Border ballBorder = BorderFactory.createTitledBorder(border, " Press 'Start Spinning!' to get this ball rolling! ", TitledBorder.CENTER, TitledBorder.CENTER, new Font("Sans Serif", style, 16), Color.white);

        // Create border for button panel:
        Border border2 = BorderFactory.createEtchedBorder(Color.blue, Color.blue);
        Border buttonBorder = BorderFactory.createTitledBorder(border2, " Press 'Stop, I'm Dizzy!' to pause the ball. ", TitledBorder.CENTER, TitledBorder.CENTER, new Font("Sans Serif", style, 12), Color.blue);
        Border padMargins = BorderFactory.createEmptyBorder(10, 10, 10, 10);

            // Create button panel:
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BorderLayout());
            buttonPanel.setBackground(Color.cyan);
            buttonPanel.setBorder(buttonBorder);

            // Create the ball panel:
            JPanel ballPanel = new JPanel() {

                // Create the beach ball graphics in the panel:
            public void paint(Graphics g) {

                super.paintComponents(g);
                g.setColor(Color.white);
                g.fillOval(X_BALL, Y_BALL, BALL_DIAMETER, BALL_DIAMETER);
                g.setColor(Color.white);
                g.drawLine(200, 100, 200, 300);
                g.drawLine(100, 200, 300, 200);

                // Using variables so they can change location overtime:
                g.setColor(Color.CYAN);
                g.fillArc(100, 100, BALL_DIAMETER, BALL_DIAMETER, blueStart, blueEnd);
                g.setColor(Color.yellow);
                g.fillArc(100, 100, BALL_DIAMETER, BALL_DIAMETER, yellowStart, yellowEnd);
                g.setColor(Color.pink);
                g.fillArc(100, 100, BALL_DIAMETER, BALL_DIAMETER, redStart, redEnd);

            }
        };

        // Finishing the ball panel:
        ballPanel.setLayout(new BorderLayout());
        ballPanel.setBackground(Color.CYAN);

        // Create PADDED panel as a container for the BALL panel:
        JPanel padding = new JPanel();
        padding.setLayout(new BorderLayout());
        padding.setBackground(Color.blue);
        padding.setBorder(padMargins);

        // Create PADDED panel as a container for the BUTTON panel:
        JPanel padding2 = new JPanel();
        padding2.setLayout(new BorderLayout());
        padding2.setBackground(Color.cyan);
        padding2.setBorder(padMargins);

        // Create TITLED panel as a container for the BALL panel:
        JPanel title = new JPanel();
        title.setLayout(new BorderLayout());
        title.setBackground(Color.blue);
        title.setBorder(ballBorder);

        // Create the start/stop buttons:
        JButton startBall = new JButton("Start Spinning!");
            startBall.addActionListener(this);
        JButton stopBall = new JButton("Stop, I'm dizzy!");
            stopBall.addActionListener(this);

        // Add components to window:
        buttonPanel.add(startBall, BorderLayout.WEST);
        buttonPanel.add(stopBall, BorderLayout.EAST);

        title.add(ballPanel, BorderLayout.CENTER);
        padding.add(title);
        padding2.add(buttonPanel, BorderLayout.PAGE_END);

        add(padding);
        add(padding2, BorderLayout.PAGE_END);

    }

    // Create event listeners for the buttons:
    @Override
    public void actionPerformed(ActionEvent e) {

    /*
    The start/true and stop/false conditions create a binary
    on/off-switch that controls the global timer and its events.

    If it wasn't spinning when start was pressed
    spinning is set to true and the global timer
    and it's events are called to happen.

    When stop is pressed it paints the current ball.
    It also sets spinning to false so that once start
    is hit again spinning is set to true and the timer
    and it's events start again.
     */

        String actionCommand = e.getActionCommand();

        if (actionCommand.equals("Start Spinning!")) {

            if (spinning == false) {

                spinning = true;
                timer.start();

            }

        } else if (actionCommand.equals("Stop, I'm dizzy!")) {

            repaint();
            spinning = false;

        }

    }

}
