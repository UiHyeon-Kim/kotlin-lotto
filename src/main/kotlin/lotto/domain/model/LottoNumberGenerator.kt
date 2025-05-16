package lotto.domain.model

object LottoNumberGenerator{
    fun generate(): Lotto {
        val number = (1..45).shuffled().take(6).sorted()
        return Lotto(number)
    }
}