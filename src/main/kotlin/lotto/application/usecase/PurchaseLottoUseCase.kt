package lotto.application.usecase

import lotto.domain.model.Lotto
import lotto.domain.model.LottoFactory

class PurchaseLottoUseCase {
    operator fun invoke(amount: Int): List<Lotto> {
        val count = amount / 1000
        return List(count) { LottoFactory.random() }
    }
}