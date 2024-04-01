import java.awt.*;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * Перечисление, описывающее варианты предопределенных фигур, предоставляемых программой
 */

public enum ShapeTypes {
    LINE {
        @Override
        public Shape getShape(int x, int y, int size, Color color) {
            return LineProducer.getLine(x,y,size,color);
        }
    },
    SQUARE {
        @Override
        public Shape getShape(int x, int y, int size, Color color) {
            return SquareProducer.getSquare(x,y,size,color);
        }
    },
    STAR {
        @Override
        public Shape getShape(int x, int y, int size, Color color) {
            return StarProducer.getStar(x,y,size,color);
        }
    };

    /**
     * Предоставляет генерацию формы, соответствующей элементу перечисления
     * @param x координата, задающее смещение фигуры от начала отсчета по оси x
     * @param y координата, задающее смещение фигуры от начала отсчета по оси y
     * @param size параметр, определяющий геометрические размеры фигуры
     * @param color цвет
     * @return объект Shape определенной формы
     */
    public abstract Shape getShape(int x, int y, int size, Color color);
    // Используется для преобразования строк в экземпляры перечисления
    private static final Map<String, ShapeTypes> stringToEnum = Stream.of(values()).collect(
            toMap(Object::toString, e->e));
    // Преобразует строку в элемент перечисления.
    static Optional<ShapeTypes> fromString(String s) {
        return Optional.ofNullable(stringToEnum.get(s));
    }
}
