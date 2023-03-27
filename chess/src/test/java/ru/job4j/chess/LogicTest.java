package ru.job4j.chess;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.QueenBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenImpossibleMoveException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        Cell start = Cell.C8;
        Cell dest = Cell.F6;
        Figure bishopBlack = new BishopBlack(start);
        logic.add(bishopBlack);
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            logic.move(bishopBlack.position(), dest);
        });
        assertThat(exception.getMessage()).isEqualTo("Could not move by diagonal from %s to %s",
                start, dest);
    }

    @Test
    public void whenMoveThenOccupiedCellException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        Cell start = Cell.C8;
        Cell dest = Cell.H3;
        Figure bishopBlack = new BishopBlack(start);
        Figure queenBlack = new QueenBlack(Cell.G4);
        logic.add(bishopBlack);
        logic.add(queenBlack);
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(bishopBlack.position(), dest);
        });
        assertThat(exception.getMessage()).isEqualTo("Ячейка занята фигурой",
                start, dest);
    }
}