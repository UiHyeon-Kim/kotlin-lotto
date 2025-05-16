package lotto.domain.model

data class Lotto(val numbers: List<Int>) {
    override fun toString(): String {
        return numbers.toString()
    }
}