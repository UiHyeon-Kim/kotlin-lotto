package lotto.presentation.view

import lotto.common.util.nullToZero
import lotto.domain.model.Lotto
import lotto.domain.model.LottoRank

class OutputView {
    fun printErrorMessage(message: String?) {
        println(message)
    }

    fun printLotteryTickets(purchaseAmount: Int, myLotteries: List<Lotto>) {
        println("\n${purchaseAmount}개를 구매했습니다.")
        for (lotto in myLotteries) {
            println(lotto.toString())
        }
    }

    fun printWinningStatistics(winningResults: Map<LottoRank, Int>, returnRate: Double) {
        println("\n당첨 통계\n---")
        printWinningTable(winningResults)
        println("총 수익률은 ${returnRate}% 입니다.")
    }

    private fun printWinningTable(winningResults: Map<LottoRank, Int>) {
        LottoRank.entries.forEach {
            println("${it.description} - ${winningResults[it].nullToZero()}개")
        }
    }
}