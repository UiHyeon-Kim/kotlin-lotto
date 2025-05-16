package lotto.application.input

import lotto.domain.model.Lotto
import lotto.domain.validator.Validator
import lotto.presentation.view.InputView

sealed class InputState<T> {
    abstract fun readRaw(view: InputView): String
    abstract fun parse(raw: String): T

    data object LottoAmount : InputState<Int>() {
        override fun readRaw(view: InputView): String = view.readLottoAmount()
        override fun parse(raw: String): Int = Validator.validateAmount(raw)
    }
    data object WinningNumbers : InputState<List<Int>>() {
        override fun readRaw(view: InputView): String = view.readWinningNumbers()
        override fun parse(raw: String): List<Int> = Validator.validateWinningNumbers(raw)
    }
    class BonusNumber(private val comparison: Lotto) : InputState<Int>() {
        override fun readRaw(view: InputView): String = view.readBonusNumber()
        override fun parse(raw: String): Int = Validator.validateBonusNumber(raw, comparison)
    }
}