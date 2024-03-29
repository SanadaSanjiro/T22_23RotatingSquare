import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Panel extends JPanel {
    private int rotationFactor;
    private int scaleFactor = 100;
    private int maxWidth, maxHeight, side;

    Shape shape;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (Objects.isNull(shape)) {
            maxWidth = getWidth();
            maxHeight = getHeight();
            side = Integer.min(maxHeight, maxWidth);
            shape = SquareProducer.getSquare((int) (side * .25), (int) (side * .25), (int) (side * .5), Color.GREEN);
            RotatingThread rt = new RotatingThread(this);
            ExecutorService es = Executors.newCachedThreadPool();
            es.execute(rt);
        }
        Plotter.plot(g, shape);
    }

    public void rotate() {
            shape.rotate(rotationFactor);
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
        shape.resize(scaleFactor);
        repaint();
    }
    public void changeShape(ShapeTypes shapeType) {
        shape = shapeType.getShape((int) (side * .25), (int) (side * .25), (int) (side * .5), Color.GREEN);
        repaint();
    }
}

public class Frame extends JFrame {
    private Panel panel = new Panel();
    private JSlider adjustCycles = new JSlider(-30, 30, 0);
    private JSlider adjustScale = new JSlider(JSlider.VERTICAL, 1, 100, 100);
    private ButtonGroup bg = new ButtonGroup();
    private JRadioButton
    jb1 = new JRadioButton(ShapeTypes.LINE.toString()),
    jb2 = new JRadioButton(ShapeTypes.SQUARE.toString()),
    jb3 = new JRadioButton(ShapeTypes.STAR.toString());

    private ActionListener buttonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShapeTypes shapeType = ShapeTypes.fromString(((JRadioButton) e.getSource()).getText()).get();
            panel.changeShape(shapeType);
        }
    };

    public Frame() {
        jb2.setSelected(true);
        jb1.addActionListener(buttonListener);
        jb2.addActionListener(buttonListener);
        jb3.addActionListener(buttonListener);
        bg.add(jb1);
        bg.add(jb2);
        bg.add(jb3);
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
        panel.add(jb1);
        panel.add(jb2);
        panel.add(jb3);
        add(panel);
        add(BorderLayout.SOUTH, adjustCycles);
        add(BorderLayout.EAST, adjustScale);
    }
}
