package br.com.promotion.ui_component.extension

import io.reactivex.Completable
import io.reactivex.Single

fun <T> Single<T>.subscribeSafe(
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit,
    finally: (() -> Unit)? = null
) =
    doFinally { finally?.invoke() }.subscribe { res, err ->
        if (err != null) {
            onError.invoke(err)
        } else {
            onSuccess.invoke(res)
        }
    }

fun Completable.subscribeSafe(
    onComplete: () -> Unit,
) = subscribeSafe(onComplete, null)


fun Completable.subscribeSafe(
    onComplete: () -> Unit,
    onError: ((Throwable) -> Unit)? = null
) =
    onErrorComplete {
        if (onError != null) {
            onError.invoke(it)
            return@onErrorComplete true
        } else {
            return@onErrorComplete false
        }
    }.subscribe {
        onComplete.invoke()
    }