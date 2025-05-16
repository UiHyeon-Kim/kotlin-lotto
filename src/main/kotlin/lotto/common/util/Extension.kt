package lotto.common.util

import java.text.DecimalFormat

fun Int.toWon(): String = DecimalFormat("#,###").format(this) + "원"

fun Int?.nullToZero(): String = this?.toString() ?: "0"