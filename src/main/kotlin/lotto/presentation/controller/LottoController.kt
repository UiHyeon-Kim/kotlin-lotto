package lotto.presentation.controller

import lotto.application.input.InputState.*
import lotto.application.input.retryInput
import lotto.application.input.InputState
import lotto.application.usecase.EvaluateLottoUseCase
import lotto.application.usecase.PurchaseLottoUseCase
import lotto.domain.model.Lotto
import lotto.domain.model.LottoFactory
import lotto.presentation.view.InputView
import lotto.presentation.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val purchaseLotto: PurchaseLottoUseCase,
    private val evaluate: EvaluateLottoUseCase
) {
    fun run() {
        val (amount: Int, myLotteries) = purchaseLottoFlow()
        val (winningNumbers: Lotto, bonusNumber: Int) = drawWinningNumbersFlow()
        evaluateWinningFlow(myLotteries, winningNumbers, bonusNumber, amount)
    }

    private fun evaluateWinningFlow(
        myLotteries: List<Lotto>,
        winningNumbers: Lotto,
        bonusNumber: Int,
        amount: Int
    ) {
        val winningResults = evaluate(myLotteries, winningNumbers, bonusNumber)
        val returnRate = evaluate.calculateReturnRate(winningResults, amount)
        outputView.printWinningStatistics(winningResults, returnRate)
    }

    private fun drawWinningNumbersFlow(): Pair<Lotto, Int> {
        val winningNumbers: Lotto = getInput(WinningNumbers).run { LottoFactory.from(this) }
        val bonusNumber: Int = getInput(BonusNumber(winningNumbers))
        return Pair(winningNumbers, bonusNumber)
    }

    private fun purchaseLottoFlow(): Pair<Int, List<Lotto>> {
        val amount: Int = getInput(LottoAmount)
        val myLotteries = purchaseLotto(amount)
        outputView.printLotteryTickets(amount, myLotteries)
        return Pair(amount, myLotteries)
    }

    private fun <T> getInput(state: InputState<T>): T = retryInput(
        read = { state.readRaw(inputView) },
        parse = { state.parse(it) },
        onError = outputView::printErrorMessage
    )
}