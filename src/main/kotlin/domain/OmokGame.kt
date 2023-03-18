package domain

class OmokGame(
    val omokBoard: OmokBoard = OmokBoard(),
) {
    fun runGame(
        getStone: () -> Stone,
        onMove: (OmokBoard, State, Stone) -> Unit,
        onMoveFail: () -> Unit,
        onForbidden: () -> Unit,
        onFinish: (State) -> Unit
    ) {
        while (true) {
            doTurn(getStone, onMoveFail, onForbidden, State.BLACK, onMove)
            if (omokBoard.isVictory(State.BLACK)) return onFinish(State.BLACK)

            doTurn(getStone, onMoveFail, onForbidden, State.WHITE, onMove)
            if (omokBoard.isVictory(State.WHITE)) return onFinish(State.WHITE)
        }
    }

    private fun doTurn(
        getStone: () -> Stone,
        onMoveFail: () -> Unit,
        onForbidden: () -> Unit,
        state: State,
        onMove: (OmokBoard, State, Stone) -> Unit
    ) {
        val stone = getStone()
        if (!omokBoard.isEmpty(stone)) {
            onMoveFail()
            return doTurn(getStone, onMoveFail, onForbidden, state, onMove)
        }

        if (state == State.BLACK && omokBoard.isForbidden(stone)) {
            onForbidden()
            return doTurn(getStone, onMoveFail, onForbidden, state, onMove)
        }

        omokBoard.move(stone, state)
        onMove(omokBoard, state.nextState(), stone)
    }
}
