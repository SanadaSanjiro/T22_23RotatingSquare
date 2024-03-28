import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Panel extends JPanel {
    private int rotationFactor;
    private int scaleFactor = 100;
    private int maxWidth, maxHeight, side;

    Shape square;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (Objects.isNull(square)) {
            maxWidth = getWidth();
            maxHeight = getHeight();
            side = Integer.min(maxHeight, maxWidth);
            square = SquareProducer.getSquare((int) (side * .25), (int) (side * .25), (int) (side * .5), Color.GREEN);
            //square = LineProducer.getLine((int) (side * .25), (int) (side * .25), (int) (side * .5), Color.GREEN);
            RotatingThread rt = new RotatingThread(this);
            ExecutorService es = Executors.newCachedThreadPool();
            //es.execute(rt);
        }
        Plotter.plot(g, square);
    }

    public void rotate() {
            square.rotate(rotationFactor);
            repaint();
    }

    public int getRotationFactor() {
        return rotationFactor;
    }
    public void setRotationFactor(int rf) {
        rotationFactor = rf;
    }

    public int getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
        square.resize(scaleFactor);
        repaint();
    }
}

public class Frame extends JFrame {
    private Panel panel = new Panel();
    private JSlider adjustCycles = new JSlider(-30, 30, 0);
    private JSlider adjustScale = new JSlider(JSlider.VERTICAL, 0, 99, 0);
    public Frame() {
        adjustCycles.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                panel.setRotationFactor(((JSlider)e.getSource()).getValue());
                System.out.println("Rotation factor: " + panel.getRotationFactor());
            }
        });
        adjustScale.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                panel.setScaleFactor(((JSlider)e.getSource()).getValue());
                System.out.println("Scale factor: " + panel.getScaleFactor());
            }
        });
        add(panel);
        add(BorderLayout.SOUTH, adjustCycles);
        add(BorderLayout.EAST, adjustScale);
    }
}
