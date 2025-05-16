package lotto.domain.model

import lotto.common.util.toWon

enum class LottoRank(
    private val prizeAmount: Int,
    private val matchCount: Int,
    private val hasBonusMatch: Boolean = false
) {
    FIFTH_PLACE(5_000, 3),
    FOURTH_PLACE(50_000, 4),
    THIRD_PLACE(1_500_000, 5),
    SECOND_PLACE(30_000_000, 5, true),
    FIRST_PLACE(2_000_000_000, 6);

    val description: String
        get() {
            val matchText = "${matchCount}개 일치"
            val bonusText = if (hasBonusMatch) ", 보너스 볼 일치" else ""
            val prizeText = " (${prizeAmount.toWon()})"
            return "$matchText$bonusText$prizeText"
        }

    companion object {
        fun from(matchCount: Int, hasBonus: Boolean = false): LottoRank? {
            return entries.find { rank ->
                rank.matchCount == matchCount && rank.hasBonusMatch == hasBonus
            }
        }
    }
}