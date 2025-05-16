package lotto

import lotto.presentation.controller.LottoController
import lotto.presentation.view.InputView
import lotto.presentation.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val lottoController = LottoController(inputView, outputView)
    lottoController.run()
}