package lotto.application.usecase

import lotto.common.constants.Constants
import lotto.domain.model.Lotto
import lotto.domain.model.LottoFactory

class PurchaseLottoUseCase {
    operator fun invoke(amount: Int): List<Lotto> {
        val count = amount / Constants.LOTTO_AMOUNT
        return List(count) { LottoFactory.random() }
    }
}