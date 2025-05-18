package lotto.presentation.controller

import lotto.application.input.InputState.*
import lotto.application.input.retryInput
import lotto.application.input.InputState
import lotto.application.usecase.EvaluateLottoUseCase
import lotto.application.usecase.PurchaseLottoUseCase
import lotto.domain.model.Lotto
import lotto.domain.model.LottoFactory
import lotto.domain.model.LotteryResult
import lotto.presentation.view.InputView
import lotto.presentation.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val purchaseLotto: PurchaseLottoUseCase,
    private val evaluate: EvaluateLottoUseCase
) {
    fun run() {
        val (amount, myLotteries) = purchaseLottoFlow()
        val lotteryResult = drawWinningNumbersFlow()
        evaluateWinningFlow(myLotteries, lotteryResult, amount)
    }

    private fun evaluateWinningFlow(
        myLotteries: List<Lotto>,
        lotteryResult: LotteryResult,
        amount: Int
    ) {
        val winningResults = evaluate(myLotteries, lotteryResult)
        val returnRate = evaluate.calculateReturnRate(winningResults, amount)
        outputView.printWinningStatistics(winningResults, returnRate)
    }

    private fun drawWinningNumbersFlow(): LotteryResult {
        val winningNumbers: Lotto = getInput(WinningNumbers).run { LottoFactory.from(this) }
        val bonusNumber: Int = getInput(BonusNumber(winningNumbers))
        return LotteryResult(winningNumbers, bonusNumber)
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