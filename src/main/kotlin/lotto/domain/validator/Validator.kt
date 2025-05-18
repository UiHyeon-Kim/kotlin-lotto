package lotto.domain.validator

import lotto.common.constants.Constants.LOTTO_AMOUNT
import lotto.common.constants.Constants.LOTTO_COUNT
import lotto.common.constants.Constants.LOTTO_MAX_NUMBER
import lotto.common.constants.Constants.LOTTO_MIN_NUMBER
import lotto.domain.model.Lotto

object Validator {
    fun validateAmount(input: String): Int {
        val amount = input.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 구매 금액은 숫자여야 합니다.")
        require(amount % LOTTO_AMOUNT == 0) { "[ERROR] 구매 금액은 1000원 단위여야 합니다." }
        return amount
    }

    fun validateWinningNumbers(input: String): List<Int> {
        val numbers = input.split(",").map {
            it.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.")
        }
        require(numbers.size == LOTTO_COUNT) { "[ERROR] 당첨 번호는 6개여야 합니다." }
        require(numbers.distinct().size == numbers.size) { "[ERROR] 당첨 번호는 중복되면 안됩니다." }
        require(numbers.all { it in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER }) { "[ERROR] 당첨 번호는 1~45 이내여야 합니다." }
        return numbers
    }

    fun validateBonusNumber(input: String, winning: Lotto): Int {
        val number = input.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.")
        require(number in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER) { "[ERROR] 보너스 번호는 1~45 이내여야 합니다." }
        require(number !in winning.numbers) { "[ERROR] 보너스 번호는 당첨번호와 중복되면 안됩니다." }
        return number
    }
}