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
        maxWidth = getWidth();
        maxHeight = getHeight();
        side = Integer.min(maxHeight, maxWidth);
        Shape square = SquareProducer.getSquare((int) (side*.25), (int) (side*.25), (int) (side*.5), Color.GREEN);
        Plotter.plot(g, square);
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
