import net.mindview.util.SwingConsole;

/**
 * Задание 23 главы 22 книги Философия Джава
 * Взяв за отправную точку пример SineWaves.java, создайте программу, которая рисует на экране вращающийся квадрат.
 * Один регулятор должен управлять скоростью вращения, а второй — размером квадрата.
 */

public class Main {
    public static void main(String[] args) {
        SwingConsole.run(new Frame(), 700, 400);
    }
}