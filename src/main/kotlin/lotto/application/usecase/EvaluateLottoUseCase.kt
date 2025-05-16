package lotto.application.usecase

import lotto.domain.model.Lotto
import lotto.domain.model.LottoRank

class EvaluateLottoUseCase {
    operator fun invoke(
        myLotteries: List<Lotto>,
        winning: Lotto,
        bonusNumber: Int
    ): Map<LottoRank, Int> {
        val result = mutableMapOf<LottoRank, Int>()
        myLotteries.forEach { lotto ->
            val matchCount = lotto.numbers.count { it in winning.numbers }
            val hasBonus = bonusNumber in lotto.numbers
            val currentRank = LottoRank.from(matchCount, hasBonus)
            currentRank?.let { result.merge(it, 1) { oldValue, _ -> oldValue + 1 } }
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