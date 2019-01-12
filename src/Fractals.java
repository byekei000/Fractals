import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Fractals extends Canvas {
    private static int width = 1080;
    private static int height = 700;
    private static int iterations = 18;
    private static double length = 200;
    private static int angle1 = 20;
    private static int angle2 = 40;
    private static double shorten = 0.7;
    private static double R = 255;
    private static double G = 0;
    private static double B = 0;
    private static double temp = 3;
    private static int branches;

    public void branches(int iteration, double len) {
        if (len >= 1) {
            if (iteration > 0) {
                iteration--;
                branches(iteration, len * shorten);
                branches(iteration, len * shorten);
                branches++;
            }
        }
    }

    public void changeColors() {
        if (R == 255 && G < 255 && B == 0) {
            G += (255.0 / branches) * temp;
        } else if (R > 0 && G == 255 && B == 0) {
            R -= (255.0 / branches) * temp;
        } else if (R == 0 && G == 255 && B < 255) {
            B += (255.0 / branches) * temp;
        } else if (R == 0 && G > 0 && B == 255) {
            G -= (255.0 / branches) * temp;
        } else if (R < 255 && G == 0 && B == 255) {
            R += (255.0 / branches) * temp;
        } else if (R == 255 && G == 0 && B > 0) {
            B -= (255.0 / branches) * temp;
        }
        if (R > 255) {
            R = 255;
        } else if (G > 255) {
            G = 255;
        } else if (B > 255) {
            B = 255;
        } else if (R < 0) {
            R = 0;
        } else if (G < 0) {
            G = 0;
        } else if (B < 0) {
            B = 0;
        }
    }

    public void branch(Graphics2D g, double len, int iteration) {
        changeColors();
        g.setColor(new Color((int) R, (int) G, (int) B));
        g.drawLine(0, 0, 0, -(int) len);
        g.translate(0, -(int) len);
        if (len >= 1) {
            if (iteration > 0) {
                iteration--;
                AffineTransform old = g.getTransform();
                g.rotate(Math.toRadians(-angle1));
                branch(g, len * shorten, iteration);
                g.setTransform(old);
                g.rotate(Math.toRadians(angle2));
                branch(g, len * shorten, iteration);
                g.setTransform(old);
            }
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.darkGray);
        g2d.fillRect(0, 0, width, height);
        g2d.translate(width / 2, height);
        branches = 0;
        branches(iterations, length);
        R = 255;
        G = 0;
        B = 0;

        branch(g2d, length, iterations);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Fractal");
        frame.setSize(width, height);
        Canvas canvas = new Fractals();
        canvas.setSize(width, height);
        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
