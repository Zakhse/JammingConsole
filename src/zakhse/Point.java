package zakhse;

import java.nio.channels.Pipe;

public class Point {
    private int x;
    private int y;

    public Point(int X, int Y) {
        if (X < 0 || Y < 0) throw new IllegalArgumentException("Both coordinates should be non-negative.");
        x = X;
        y = Y;
    }

    public Point() {
        x = -1;
        y = -1;
    }

    public int getX() {return x;}

    public int getY() {return y;}

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return x == point.x && y == point.y;

    }
}
