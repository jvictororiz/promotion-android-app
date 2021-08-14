package br.com.promotion.core.extensions

fun <T> T.runIf(add: Boolean, block: (T) -> T): T {
    return if (add) {
        block(this)
    } else {
        this
    }
}