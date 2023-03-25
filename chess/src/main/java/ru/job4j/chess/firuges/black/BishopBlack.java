package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }

    @Override
    public Cell[] way(Cell dest) {
       if (!isDiagonal(position, dest)) {
            throw new ImpossibleMoveException(
                    String.format("Could not move by diagonal from %s to %s", position, dest)
            );
        }
        //
        int xp = position.getX();
        int yp = position.getY();
        int x = xp;
        int y = yp;
        int xd = dest.getX();
        int yd = dest.getY();
        int deltaX = 0;
        int deltaY = 0;
        int size = Math.abs(xp - xd);
        Cell[] steps = new Cell[size];
        //вверх в лево
        if(xp > xd && yp < yd) {
            deltaX = -1;
            deltaY = 1;
        }
        // вниз в лево
        if(xp > xd && yp > yd) {
            deltaX = -1;
            deltaY = -1;
        }
        //вверх вправо
        if(xp < xd && yp > yd) {
            deltaX = 1;
            deltaY = -1;
        }
        //вниз в право
        if(xp < xd && yp < yd) {
            deltaX = 1;
            deltaY = 1;
        }
        for (int index = 0; index < size; index++) {
        x += deltaX;
        y += deltaY;
        steps[index] = Cell.findBy(x, y);
         }
        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        if (Math.abs(source.getX() - dest.getX()) == Math.abs(source.getY() - dest.getY())) {
            return true;
        }

        return false;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
