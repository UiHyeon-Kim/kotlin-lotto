package lotto.domain.utils

fun <T> retryInput(
    read: () -> String,
    parse: (String) -> T,
    onError: (String?) -> Unit
): T {
    while (true) {
        runCatching {
            return parse(read())
        }.onFailure {
            onError(it.message)
        }
    }
}