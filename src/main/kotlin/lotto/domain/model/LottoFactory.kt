package lotto.domain.model

import lotto.common.constants.Constants.LOTTO_COUNT
import lotto.common.constants.Constants.LOTTO_MAX_NUMBER
import lotto.common.constants.Constants.LOTTO_MIN_NUMBER

object LottoFactory {
    fun from(numbers: List<Int>) = Lotto(numbers)
    fun random() = Lotto((LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER).shuffled().take(LOTTO_COUNT).sorted())
}