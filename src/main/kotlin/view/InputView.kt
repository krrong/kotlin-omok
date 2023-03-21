package view

import domain.Stone

object InputView {

    private const val MESSAGE_STONE = "위치를 입력하세요: "

    fun readPosition(): Stone {
        print(MESSAGE_STONE)
        val input = readln()
        return runCatching {
            Stone.create(input[0], input.substring(1).toInt())
        }.getOrElse { error ->
            println(error.message)
            readPosition()
        }
    }
}
