package lotto.domain.utils

object Validator {
    fun validateAmount(input: String): Int {
        val amount = input.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 구매 금액은 숫자여야 합니다.")
        require(amount % 1000 == 0) { "[ERROR] 구매 금액은 1000원 단위여야 합니다." }
        return amount
    }

    fun validateWinningNumbers(input: String): List<Int> =
        input.split(",").map { it.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.") }

    fun validateBonusNumber(input: String, winningNumbers: List<Int>): Int {
        val number = input.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.")
        require(number in 1..45) { "[ERROR] 보너스 번호는 1~45 이내여야 합니다." }
        require(number !in winningNumbers)
        return number
    }
}