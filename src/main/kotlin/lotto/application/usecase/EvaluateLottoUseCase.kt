package lotto.application.usecase

import lotto.common.constants.Constants.DEFAULT_VALUE
import lotto.domain.model.LotteryResult
import lotto.domain.model.Lotto
import lotto.domain.model.LottoRank

class EvaluateLottoUseCase {
    operator fun invoke(
        myLotteries: List<Lotto>,
        lottery: LotteryResult
    ): Map<LottoRank, Int> {
        val result = mutableMapOf<LottoRank, Int>()
        myLotteries.forEach { lotto ->
            val matchCount = lotto.numbers.count { it in lottery.winning.numbers }
            val hasBonus = lottery.bonus in lotto.numbers
            val currentRank = LottoRank.from(matchCount, hasBonus)
            currentRank?.let { result.merge(it, DEFAULT_VALUE) { oldValue, newValue -> oldValue + newValue } }
        }
        return result
    }

    fun calculateReturnRate(
        results: Map<LottoRank, Int>,
        purchaseAmount: Int
    ): Double {
        val profit = results.entries.sumOf { (rank, count) -> rank.prizeAmount * count }
        return profit.toDouble() / purchaseAmount * 100
    }
}