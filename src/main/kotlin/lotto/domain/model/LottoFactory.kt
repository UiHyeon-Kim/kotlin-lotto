package lotto.domain.model

object LottoFactory {
    fun from(numbers: List<Int>) = Lotto(numbers)
    fun random() = Lotto((1..45).shuffled().take(6).sorted())
}