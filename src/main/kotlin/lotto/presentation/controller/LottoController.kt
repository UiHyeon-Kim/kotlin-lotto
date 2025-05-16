package lotto.presentation.controller

import lotto.application.input.InputState.*
import lotto.application.input.retryInput
import lotto.application.input.InputState
import lotto.presentation.view.InputView
import lotto.presentation.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun run() {
        val purchaseAmount: Int = getInput(LottoAmount)
        val winningNumbers: List<Int> = getInput(WinningNumbers)
        val bonusNumber: Int = getInput(BonusNumber(winningNumbers))

    }

    private fun <T> getInput(state: InputState<T>): T = retryInput(
        read = { state.readRaw(inputView) },
        parse = { state.parse(it) },
        onError = outputView::printErrorMessage
    )


}