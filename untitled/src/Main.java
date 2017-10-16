import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BAIL_OUT = 20;
    private static final int STEP_X = 30;
    private static final int STEP_Y = 20;
    private static final double SX = 0.005;
    private static final double SY = 0.005;
    private static final int COUNT_ITER = 15000;
    private static double dx = -315;
    private static double dy = -275;

    private static void drawClouds(Graphics g) {
        for (int i = 0; i < WIDTH; i += STEP_X) {
            for (int j = 0; j < HEIGHT; j += STEP_Y) {
                double c = SX * (i + dx);
                double d = SY * (j + dy);
                double x = c;
                double y = d;
                double t;
                int k = 0;
                g.setColor(new Color((float)Math.random(),
                        (float)Math.random(), (float)Math.random()));
                while (x * x + y * y < BAIL_OUT && k < COUNT_ITER) {
                    t = x * x - y * y + c;
                    y = 2 * x * y + d;
                    x = t;
                    g.drawOval((int) (x / SX - dx), (int) (y / SY - dy), 1, 1);
                    ++k;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame jFrame = new JFrame();
        jFrame.addNotify();
        jFrame.setSize(jFrame.getInsets().left +
                        jFrame.getInsets().right + WIDTH,
                jFrame.getInsets().top +
                        jFrame.getInsets().bottom + HEIGHT);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {

                jFrame.add(new JPanel() {
                    @Override
                    public void paintComponent(Graphics g) {
                        super.setBackground(Color.BLACK);
                        super.paintComponent(g);
                        drawClouds(g);
                    }
                });
                jFrame.setVisible(true);
                dx = dx-Math.random();
                dy = dy+Math.random();

                Thread.sleep(100L);

            }
        }
    }
