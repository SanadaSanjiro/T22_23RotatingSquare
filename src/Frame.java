import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс, задающий графический интерфейс
 */
class Panel extends JPanel {
    private int rotationFactor;
    private int scale = 100;
    private int side;
    Shape shape;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (Objects.isNull(shape)) {
            int maxWidth = getWidth();
            int maxHeight = getHeight();
            side = Integer.min(maxHeight, maxWidth);
            shape = SquareProducer.getSquare((int) (side * .25), (int) (side * .25), (int) (side * .5), Color.GREEN);
            RotatingThread rt = new RotatingThread(this);
            ExecutorService es = Executors.newCachedThreadPool();
            es.execute(rt);                             // запускаем поток, придающий фигуре вращение
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

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
        shape.resize(scale);
        repaint();
    }

    /**
     * Заменяет отображаемую фигуру на другую
     * @param shapeType Один из типов заранее предопределенных фигур
     */
    public void changeShape(ShapeTypes shapeType) {
        shape = shapeType.getShape((int) (side * .25), (int) (side * .25), (int) (side * .5), Color.GREEN);
        repaint();
    }
}

public class Frame extends JFrame {
    private final Panel panel = new Panel();

    public Frame() {
        JRadioButton jb2 = new JRadioButton(ShapeTypes.SQUARE.toString());
        jb2.setSelected(true);
        JRadioButton jb1 = new JRadioButton(ShapeTypes.LINE.toString());
        ActionListener buttonListener = e -> {
            Optional<ShapeTypes> optional = ShapeTypes.fromString(((JRadioButton) e.getSource()).getText());
            if (optional.isEmpty()) return;
            ShapeTypes shapeType = optional.get();
            panel.changeShape(shapeType);
        };
        jb1.addActionListener(buttonListener);
        jb2.addActionListener(buttonListener);
        JRadioButton jb3 = new JRadioButton(ShapeTypes.STAR.toString());
        jb3.addActionListener(buttonListener);
        ButtonGroup bg = new ButtonGroup();
        bg.add(jb1);
        bg.add(jb2);
        bg.add(jb3);
        JSlider adjustCycles = new JSlider(-30, 30, 0);
        adjustCycles.setToolTipText("Rotation Factor");
        adjustCycles.addChangeListener(e -> panel.setRotationFactor(((JSlider)e.getSource()).getValue()));
        JSlider adjustScale = new JSlider(JSlider.VERTICAL, 1, 100, 100);
        adjustScale.setToolTipText("Scale");
        adjustScale.addChangeListener(e -> panel.setScale(((JSlider)e.getSource()).getValue()));
        panel.add(jb1);
        panel.add(jb2);
        panel.add(jb3);
        add(panel);
        add(BorderLayout.SOUTH, adjustCycles);
        add(BorderLayout.EAST, adjustScale);
    }
}
