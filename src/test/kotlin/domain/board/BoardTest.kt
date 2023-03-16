package domain.board

import domain.State
import domain.Stone
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun `2, 3에 흑돌을 착수하면 2, 3 위치가 Black이다`() {
        val board = Board()
        board.move(Stone(2, 3), State.BLACK)

        assertThat(board.value[2][3]).isEqualTo(State.BLACK)
    }

    @Test
    fun `2, 2에 흑돌을 착수하면 2, 2 위치가 Black이다`() {
        val board = Board()
        board.move(Stone(2, 2), State.BLACK)

        assertThat(board.value[2][2]).isEqualTo(State.BLACK)
    }

    @Test
    fun `1, 1에 돌이 있으면 isEmpty의 결과는 false다`() {
        val board = Board()
        board.move(Stone(1, 1), State.BLACK)

        assertThat(board.isEmpty(Stone(1, 1))).isFalse
    }

    @Test
    fun `1, 1에 백돌을 착수하면 1, 1 위치가 White이다`() {
        val board = Board()
        board.move(Stone(1, 1), State.WHITE)

        assertThat(board.value[1][1]).isEqualTo(State.WHITE)
    }
}