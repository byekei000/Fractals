import javax.swing.*;

public class Pong {
    public static void main(String[] args) {
        int width = 500;
        int height = 300;
        GamePlay gameplay = new GamePlay(width, height);
        JFrame frame = new JFrame("Pong");
        frame.setSize(width, height);
        frame.setUndecorated(true);
        gameplay.setSize(width, height);
        frame.add(gameplay);
//        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
