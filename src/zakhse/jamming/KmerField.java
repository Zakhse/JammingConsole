package zakhse.jamming;

import zakhse.Point;

import java.util.HashSet;
import java.util.Set;

enum Arrangement {VERTICAL, HORIZONTAL}

public class KmerField {

    private int size;

    public int getSize() {return size;}

    private enum Filling {FULL, EMPTY}

    private kmerCell[][] field;

    public void generateField(int size) {
        this.size = size;
        field = new kmerCell[size][];
        for (int i = 0; i < size; i++) {
            field[i] = new kmerCell[size];
            for (int j = 0; j < size; j++)
                field[i][j] = new kmerCell();
        }

        Set<Point> pointSet = new HashSet<>();
    }

    private class kmerCell {
        private Point point;
        private Arrangement arrangement;
        Filling filling;

        private int getHeadX() {return point.getX();}

        private int getHeadY() {return point.getY();}

        private Arrangement getArrangement() {return arrangement;}

        kmerCell(int headX, int headY, Arrangement arrangement) {
            this.arrangement = arrangement;
            point = new Point(headX, headY);
            filling = Filling.FULL;
        }

        kmerCell() {
            point = new Point();
            filling = Filling.EMPTY;
        }

    }
}
