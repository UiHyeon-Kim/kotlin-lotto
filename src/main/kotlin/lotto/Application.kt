package lotto

import lotto.application.usecase.EvaluateLottoUseCase
import lotto.application.usecase.PurchaseLottoUseCase
import lotto.presentation.controller.LottoController
import lotto.presentation.view.InputView
import lotto.presentation.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val purchaseLotto = PurchaseLottoUseCase()
    val evaluate = EvaluateLottoUseCase()
    val lottoController = LottoController(inputView, outputView, purchaseLotto, evaluate)
    lottoController.run()
}