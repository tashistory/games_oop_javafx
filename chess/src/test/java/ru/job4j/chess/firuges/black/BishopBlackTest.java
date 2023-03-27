package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;

class BishopBlackTest {

    @Test
    void whenTruePosition() {
        Cell expected = Cell.A4;
        Figure bishopBlack = new BishopBlack(expected);
        assertThat(bishopBlack.position()).isEqualTo(expected);
    }

    @Test
    void whenTrueway() {
        Cell[] expected = new Cell[]{Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        Figure bishopBlack = new BishopBlack(Cell.C1);
        assertThat(bishopBlack.way(Cell.G5)).isEqualTo(expected);
    }

    @Test
    void whenTrueСopy() {
        Cell expected = Cell.A4;
        Figure bishopBlack = new BishopBlack(expected);
        Cell copy = Cell.B5;
        assertThat(copy).isEqualTo(bishopBlack.copy(copy).position());
    }
}