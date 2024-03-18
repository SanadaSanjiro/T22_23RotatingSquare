import javax.swing.*;
import java.awt.*;

class Square extends JPanel {
    private int rotationFactor = 100;
    private int scaleFactor = 100;
    private int degree = 0;
    private int[][] vertices;
    private int maxWidth, maxHeight, side ;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        maxWidth = getWidth();
        maxHeight = getHeight();
        side = Integer.min(maxHeight, maxWidth);
        vertices = new int[][] {
                {(int) (side*.25) , (int) (side*.25)},
                {(int) (side*.75) , (int) (side*.25)},
                {(int) (side*.75) , (int) (side*.75)},
                {(int) (side*.25) , (int) (side*.75)}
        };

        for (int i = 0; i < 4; i ++) {
            if (i<3) {
                g.drawLine(vertices[i][0], vertices[i][1], vertices[i+1][0], vertices[i+1][1]);
            } else {
                g.drawLine(vertices[i][0], vertices[i][1], vertices[0][0], vertices[0][1]);
            }
        }
    }

    public void rotate() {
        degree++;

    }
}

public class Panel extends JFrame {
    private Square square = new Square();
    public Panel() {
        add(square);
    }
}
