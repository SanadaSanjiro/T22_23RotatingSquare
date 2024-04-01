import java.util.concurrent.TimeUnit;

/**
 * Процесс, обеспечивающий вращение фигуры
 */
public class RotatingThread implements Runnable{
    Panel panel;
    RotatingThread(Panel p) {
        panel = p;
    }
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                TimeUnit.MILLISECONDS.sleep(40 );
                panel.rotate();
            } catch (InterruptedException e) {
                System.out.println("Работа прервана");
            }
        }
    }
}
