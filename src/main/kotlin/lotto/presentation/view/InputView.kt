package lotto.presentation.view

class InputView {
    fun readLottoAmount(): String {
        println("구매 금액을 입력해 주세요.")
        return readln()
    }

    fun readWinningNumbers(): String {
        println("당첨 번호를 입력해 주세요.")
        return readln()
    }

    fun readBonusNumber(): String {
        println("보너스 번호를 입력해 주세요.")
        return readln()
    }
}