import java.awt.*;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum ShapeTypes {
    LINE {
        @Override
        public Shape getShape(int x, int y, int edgeLenght, Color color) {
            return LineProducer.getLine(x,y,edgeLenght,color);
        }
    },
    SQUARE {
        @Override
        public Shape getShape(int x, int y, int edgeLenght, Color color) {
            return SquareProducer.getSquare(x,y,edgeLenght,color);
        }
    },
    STAR {
        @Override
        public Shape getShape(int x, int y, int edgeLenght, Color color) {
            return StarProducer.getStar(x,y,edgeLenght,color);
        }
    };
    public abstract Shape getShape(int x, int y, int edgeLenght, Color color);
    private static final Map<String, ShapeTypes> stringToEnum = Stream.of(values()).collect(
            toMap(Object::toString, e->e));
    static Optional<ShapeTypes> fromString(String s) {
        return Optional.ofNullable(stringToEnum.get(s));
    }
}
