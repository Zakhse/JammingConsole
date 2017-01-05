package zakhse.jamming;

import zakhse.Point;

import java.util.*;

enum Arrangement {VERTICAL, HORIZONTAL}

public class KmerField {
    private Point getRandom(Set<Point> s) {
        int l = rnd.nextInt(s.size());
        int i = 0;
        for (Point p : s)
            if (i++ == l) return p;
        return null;
    }

    private static Random rnd = new Random();

    private int size;
    private int kmerSize;
    private boolean generated = false;

    public boolean isGenerated() {return generated;}

    public int getSize() {return size;}

    public int getKmerSize() {return kmerSize;}

    @Override
    public String toString() {
        if (!generated) return "";
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j].filling == Filling.EMPTY) str.append("  ");
                else if (field[i][j].arrangement == Arrangement.HORIZONTAL) str.append("- ");
                else str.append("| ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    private enum Filling {FULL, EMPTY}

    private kmerCell[][] field;

    public void generateField(int size, int kmerSize) {
        if (size < kmerSize) throw new IllegalArgumentException("Size field cannot be less than size of k-mers.");

        this.size = size;
        this.kmerSize = kmerSize;
        field = new kmerCell[size][];

        for (int i = 0; i < size; i++) {
            field[i] = new kmerCell[size];
            for (int j = 0; j < size; j++)
                field[i][j] = new kmerCell();
        }


        Set<Point> pointSetHorizontal = new HashSet<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - kmerSize + 1; j++) {
                pointSetHorizontal.add(new Point(i, j));
            }
        }

        Set<Point> pointSetVertical = new HashSet<>();
        for (int i = 0; i < size - kmerSize + 1; i++) {
            for (int j = 0; j < size; j++) {
                pointSetVertical.add(new Point(i, j));
            }
        }

        while (!pointSetHorizontal.isEmpty() || !pointSetVertical.isEmpty()) {

            Arrangement chosenArrangement;
            if (pointSetHorizontal.isEmpty()) chosenArrangement = Arrangement.VERTICAL;
            else if (pointSetVertical.isEmpty()) chosenArrangement = Arrangement.HORIZONTAL;
            else if (rnd.nextBoolean()) chosenArrangement = Arrangement.HORIZONTAL;
            else chosenArrangement = Arrangement.VERTICAL;

            Point chosenPoint;

            if (chosenArrangement == Arrangement.HORIZONTAL) {
                chosenPoint = getRandom(pointSetHorizontal);
            } else {
                chosenPoint = getRandom(pointSetVertical);
            }

            int X = chosenPoint.getX();
            int Y = chosenPoint.getY();
            kmerCell kmerToPlace = new kmerCell(X, Y, chosenArrangement);

            if (chosenArrangement == Arrangement.HORIZONTAL)
                for (int j = Y; j < Y + kmerSize; j++) {
                    field[X][j] = kmerToPlace;
                }
            else
                for (int i = X; i < X + kmerSize; i++) {
                    field[i][Y] = kmerToPlace;
                }
            if (chosenArrangement == Arrangement.HORIZONTAL) {
                // Cleans set of point fo horizontal kmers
                for (int i = Math.max(0, Y - kmerSize + 1); i <= Y + kmerSize - 1; i++)
                    pointSetHorizontal.remove(new Point(X, i));

                // Cleans set of point for vertical kmers
                for (int j = Y; j < Y + kmerSize; j++)
                    for (int i = Math.max(0, X - kmerSize + 1); i <= X; i++)
                        pointSetVertical.remove(new Point(i, j));

            } else {
                // Cleans set of point for vertical kmers
                for (int i = Math.max(0, X - kmerSize + 1); i <= X + kmerSize - 1; i++)
                    pointSetVertical.remove(new Point(i, Y));

                // Cleans set of point for horizontal kmers
                for (int i = X; i < X + kmerSize; i++)
                    for (int j = Math.max(0, Y - kmerSize + 1); j <= Y; j++)
                        pointSetHorizontal.remove(new Point(i, j));

            }
        }
        generated = true;
    } // generation

    private class kmerCell {
        private Point point;
        private Arrangement arrangement;
        Filling filling;

        public int getHeadX() {return point.getX();}

        public int getHeadY() {return point.getY();}

        public Arrangement getArrangement() {return arrangement;}

        public kmerCell(int headX, int headY, Arrangement arrangement) {
            this.arrangement = arrangement;
            point = new Point(headX, headY);
            filling = Filling.FULL;
        }

        public kmerCell() {
            point = new Point();
            filling = Filling.EMPTY;
        }

    }
}
