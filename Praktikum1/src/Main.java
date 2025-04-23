import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    static class Frame extends JFrame {
        BorderLayout root = new BorderLayout();
        Canvas canvas = new Canvas();

        Frame() {
            super();

            setBounds(0, 0, 800, 500);
            setLayout(root);
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            add(canvas);

            setVisible(true);
        }

        class Canvas extends JPanel {
            Canvas() {
                super();
            }
            void draw() {
                Graphics g = getGraphics();
                // do stuff
            }
        }
    }    

    public static void main(String[] args) {
        new Frame();
    }
}
