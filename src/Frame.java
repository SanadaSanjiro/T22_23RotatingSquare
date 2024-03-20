import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Objects;

class Panel extends JPanel {
    private int rotationFactor = 100;
    private int scaleFactor = 100;
    private int degree = 0;
    private int[][] vertices;
    private int maxWidth, maxHeight, side;

    Shape square;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        maxWidth = getWidth();
        maxHeight = getHeight();
        side = Integer.min(maxHeight, maxWidth);
        if (Objects.isNull(square))
               square = SquareProducer.getSquare((int) (side*.25), (int) (side*.25), (int) (side*.5), Color.GREEN);
        Plotter.plot(g, square);
    }

    public void rotate(int angle) {
            Rotator.rotate(square, angle);
            repaint();
    }
}

public class Frame extends JFrame {
    private Panel panel = new Panel();
    private JSlider adjustCycles = new JSlider(-90, 90, 0);
    public Frame() {
        adjustCycles.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                panel.rotate(
                        ((JSlider)e.getSource()).getValue());
            }
        });
        add(panel);
        add(BorderLayout.SOUTH, adjustCycles);
    }
}
