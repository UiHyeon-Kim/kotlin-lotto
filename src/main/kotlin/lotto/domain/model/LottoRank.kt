package lotto.domain.model

enum class LottoRank(
    val prizeAmount: Int,
    val matchCount: Int,
    val hasBonusMatch: Boolean = false
) {
    FIFTH_PLACE(5_000, 3),
    FOURTH_PLACE(50_000, 4),
    THIRD_PLACE(1_500_000, 5),
    SECOND_PLACE(30_000_000, 5, true),
    FIRST_PLACE(2_000_000_000, 6);
}